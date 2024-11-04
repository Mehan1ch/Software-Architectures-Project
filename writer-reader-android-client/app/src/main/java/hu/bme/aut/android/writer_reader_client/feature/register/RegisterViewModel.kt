package hu.bme.aut.android.writer_reader_client.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class RegisterViewIntent {
    data class UsernameChanged(val username: String) : RegisterViewIntent()
    data class EmailChanged(val email: String) : RegisterViewIntent()
    data class PasswordChanged(val password: String) : RegisterViewIntent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterViewIntent()
    data class TermsAndConditionsChanged(val isTermsAndConditionsChecked: Boolean) : RegisterViewIntent()
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
    val isTermsAndConditionsChecked: Boolean = false,
    val isUsernameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isConfirmPasswordError: Boolean = false
)


class RegisterViewModel: ViewModel() {
    private val _state = MutableStateFlow(RegisterViewState())
    val state = _state.asStateFlow()

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

            is RegisterViewIntent.TermsAndConditionsChanged -> {
                _state.value = _state.value.copy(
                    isTermsAndConditionsChecked = intent.isTermsAndConditionsChecked
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


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RegisterViewModel()
            }
        }
    }

    fun register(){
        /*TODO*/
    }
}