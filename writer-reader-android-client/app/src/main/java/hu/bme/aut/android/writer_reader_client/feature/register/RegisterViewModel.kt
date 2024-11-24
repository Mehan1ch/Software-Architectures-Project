package hu.bme.aut.android.writer_reader_client.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.repository.ApiManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class RegisterViewIntent {
    data class UsernameChanged(val username: String) : RegisterViewIntent()
    data class EmailChanged(val email: String) : RegisterViewIntent()
    data class PasswordChanged(val password: String) : RegisterViewIntent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterViewIntent()
    object TogglePasswordVisibility : RegisterViewIntent()
    object ToggleConfirmPasswordVisibility : RegisterViewIntent()
    object RegisterButtonClicked : RegisterViewIntent()
}

sealed class RegisterUiEvent {
    object RegisterSuccessful : RegisterUiEvent()
    data class RegisterFailed(val error: String) : RegisterUiEvent()
}

data class RegisterViewState (
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isUsernameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isConfirmPasswordError: Boolean = false
)


class RegisterViewModel(
    private val apiManager: ApiManager
): ViewModel() {
    private val _state = MutableStateFlow(RegisterViewState())
    val state = _state.asStateFlow()

    private val _event = Channel<RegisterUiEvent>()
    val event = _event.receiveAsFlow()



    fun onIntent(intent: RegisterViewIntent) {
        when (intent) {
            is RegisterViewIntent.UsernameChanged -> {
                _state.value = _state.value.copy(
                    username = intent.username,
                    isUsernameError = false
                )
            }
            is RegisterViewIntent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = intent.email,
                    isEmailError = false
                )

            }
            is RegisterViewIntent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = intent.password,
                    isPasswordError = false
                )
            }
            is RegisterViewIntent.ConfirmPasswordChanged -> {
                _state.value = _state.value.copy(
                    confirmPassword = intent.confirmPassword,
                    isConfirmPasswordError = false
                )
            }



            is RegisterViewIntent.TogglePasswordVisibility -> {
                _state.value = _state.value.copy(
                    isPasswordVisible = !_state.value.isPasswordVisible
                )
            }

            is RegisterViewIntent.ToggleConfirmPasswordVisibility -> {
                _state.value = _state.value.copy(
                    isConfirmPasswordVisible = !_state.value.isConfirmPasswordVisible
                )
            }

            is RegisterViewIntent.RegisterButtonClicked -> {
                register()
            }
        }
    }


    private fun register() {
        viewModelScope.launch {
            println(_state.value.username)
            println(_state.value.email)
            println(_state.value.password)
            println(_state.value.confirmPassword)

            apiManager.register(
                request = RegisterRequest(
                    name = _state.value.username,
                    email = _state.value.email,
                    password =_state.value.password,
                    passwordConfirmation = _state.value.confirmPassword
                ),
                onSuccess = { response ->
                    _event.trySend(RegisterUiEvent.RegisterSuccessful)
                    println("Registration response: $response")
                    println("Registration successful!")
                },
                onError = { errorMessage ->
                    _event.trySend(RegisterUiEvent.RegisterFailed(errorMessage))
                    println("Registration failed: $errorMessage")
                }
            )
        }
    }



    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RegisterViewModel(
                    apiManager = WriterReaderApplication.apiManager
                )
            }
        }
    }

}