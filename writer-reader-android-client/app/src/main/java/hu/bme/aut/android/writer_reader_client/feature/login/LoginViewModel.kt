package hu.bme.aut.android.writer_reader_client.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class LoginViewIntent {
    data class UsernameChanged(val username: String) : LoginViewIntent()
    data class PasswordChanged(val password: String) : LoginViewIntent()
    object LoginButtonClicked : LoginViewIntent()
    object RegisterButtonClicked : LoginViewIntent()
    object TogglePasswordVisibility : LoginViewIntent()
}


data class LoginViewState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUsernameError: Boolean = false,
    val isPasswordError: Boolean = false
)

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    fun onIntent(intent: LoginViewIntent) {
        viewModelScope.launch {
            when (intent) {
                is LoginViewIntent.UsernameChanged -> {
                    _state.value = _state.value.copy(username = intent.username)
                }

                is LoginViewIntent.PasswordChanged -> {

                }

                is LoginViewIntent.LoginButtonClicked -> {}

                is LoginViewIntent.RegisterButtonClicked -> {}

                is LoginViewIntent.TogglePasswordVisibility -> {}
            }
        }
    }
}