package hu.bme.aut.android.writer_reader_client.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
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
    private val api: WriterReaderApi
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
            try {
                val request = RegisterRequest(
                    name = _state.value.username,
                    email = _state.value.email,
                    password = _state.value.password,
                    passwordConfirmation = _state.value.confirmPassword
                )
                println(_state.value.email)
                println(_state.value.password)
                val response = api.register(request)
                println("Fostömlődés megnyitása")

                if (response.isSuccessful) {
                    println("Registration successful!")
                    _event.send(RegisterUiEvent.RegisterSuccessful)
                } else {
                    println("Login failed: ${response.errorBody()?.string()}")
                    _event.send(RegisterUiEvent.RegisterFailed(response.errorBody()?.string() ?: "Unknown error"))

                }

            }catch (e: Exception) {
                _event.send(RegisterUiEvent.RegisterFailed(e.message ?: "Unknown error"))
            }

        }
    }



    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RegisterViewModel(
                    api = WriterReaderApplication.api
                )
            }
        }
    }

}