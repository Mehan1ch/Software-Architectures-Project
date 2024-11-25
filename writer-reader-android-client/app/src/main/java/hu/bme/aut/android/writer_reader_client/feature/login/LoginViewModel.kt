package hu.bme.aut.android.writer_reader_client.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginRequest
import hu.bme.aut.android.writer_reader_client.data.preferences.DataStoreManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import android.content.Context
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import hu.bme.aut.android.writer_reader_client.data.repository.ApiManager


sealed class LoginViewIntent {
    data class EmailChanged(val email: String) : LoginViewIntent()
    data class PasswordChanged(val password: String) : LoginViewIntent()
    data class LoginButtonClicked(val context: Context, val onSuccessfulLogin: () -> Unit) : LoginViewIntent()
    object TogglePasswordVisibility : LoginViewIntent()
    object ErrorOkButtonClicked : LoginViewIntent()
}


data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUsernameError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isError: Boolean = false,
    val throwable: Throwable? = null
)


sealed class LoginUiEvent {
    object LoginSuccessful : LoginUiEvent()
    data class LoginFailed(val error: String) : LoginUiEvent()
}

class LoginViewModel(
    private val apiManager: ApiManager
): ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    private val _event = Channel<LoginUiEvent>()
    val event = _event.receiveAsFlow()



    fun onIntent(intent: LoginViewIntent) {
        viewModelScope.launch {
            when (intent) {
                is LoginViewIntent.ErrorOkButtonClicked -> {
                    _state.value = _state.value.copy(
                        isError = false,
                        throwable = null
                    )
                }
                is LoginViewIntent.EmailChanged -> {
                    _state.value = _state.value.copy(
                        email = intent.email,
                        isUsernameError = false
                    )
                }

                is LoginViewIntent.PasswordChanged -> {
                    _state.value = _state.value.copy(
                        password = intent.password,
                        isPasswordError = false
                    )
                }

                is LoginViewIntent.TogglePasswordVisibility -> {
                    _state.value = _state.value.copy(
                        isPasswordVisible = !_state.value.isPasswordVisible
                    )
                }


                is LoginViewIntent.LoginButtonClicked -> {
                    login(intent.context, intent.onSuccessfulLogin)
                }
            }
        }
    }

    private fun login(context: Context, onSuccessfulLogin: () -> Unit) {
        viewModelScope.launch {
            apiManager.login(
                LoginRequest(
                    email = _state.value.email,
                    password = _state.value.password
                ),
                onSuccess = { loginResponse ->
                    launch {
                        DataStoreManager.storeUserToken(context, loginResponse.token)
                        DataStoreManager.storeUserEmail(context, _state.value.email)
                        DataStoreManager.getUserTokenFlow(context).collect { token ->
                            println("Token: $token")
                        }
                        onSuccessfulLogin()
                    }
                    _event.trySend(LoginUiEvent.LoginSuccessful)
                    println("Login successful: $loginResponse")
                    println(_event)
                },
                onError = { errorMessage ->
                    _event.trySend(LoginUiEvent.LoginFailed(errorMessage))
                    println("Error: $errorMessage")
                    println(_event)
                    _state.value = _state.value.copy(
                        isError = true,
                        throwable = Exception(errorMessage)
                    )
                }
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    apiManager = WriterReaderApplication.apiManager
                )
            }
        }
    }

}