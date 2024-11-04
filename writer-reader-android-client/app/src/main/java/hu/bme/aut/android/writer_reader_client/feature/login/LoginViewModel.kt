package hu.bme.aut.android.writer_reader_client.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


sealed class LoginViewIntent {
    data class UsernameChanged(val username: String) : LoginViewIntent()
    data class PasswordChanged(val password: String) : LoginViewIntent()
    object LoginButtonClicked : LoginViewIntent()
    object TogglePasswordVisibility : LoginViewIntent()
}


data class LoginViewState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUsernameError: Boolean = false,
    val isPasswordError: Boolean = false
)


sealed class LoginUiEvent {
    object LoginSuccessful : LoginUiEvent()
    data class LoginFailed(val error: String) : LoginUiEvent()
}

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    private val _event = Channel<LoginUiEvent>()
    val event = _event.receiveAsFlow()


    fun onIntent(intent: LoginViewIntent) {
        viewModelScope.launch {
            when (intent) {
                is LoginViewIntent.UsernameChanged -> {
                    _state.value = _state.value.copy(
                        username = intent.username,
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
                    login()
                }
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            try {
                /*TODO*/

                _event.send(LoginUiEvent.LoginSuccessful)
            }catch (e: Exception) {
                _event.send(LoginUiEvent.LoginFailed(e.message ?: "Unknown error"))
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel()
            }
        }
    }

}