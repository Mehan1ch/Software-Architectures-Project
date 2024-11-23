package hu.bme.aut.android.writer_reader_client.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.preferences.DataStoreManager
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import android.content.Context
import androidx.compose.ui.platform.LocalContext


sealed class LoginViewIntent {
    data class EmailChanged(val email: String) : LoginViewIntent()
    data class PasswordChanged(val password: String) : LoginViewIntent()
    data class LoginButtonClicked(val context: Context) : LoginViewIntent()
    object TogglePasswordVisibility : LoginViewIntent()
}


data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isUsernameError: Boolean = false,
    val isPasswordError: Boolean = false
)


sealed class LoginUiEvent {
    object LoginSuccessful : LoginUiEvent()
    data class LoginFailed(val error: String) : LoginUiEvent()
}

class LoginViewModel(
    private val api: WriterReaderApi
): ViewModel() {
    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    private val _event = Channel<LoginUiEvent>()
    val event = _event.receiveAsFlow()


    fun onIntent(intent: LoginViewIntent) {
        viewModelScope.launch {
            when (intent) {
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
                    login(intent.context)
                }
            }
        }
    }

    private fun login(context: Context) {
        viewModelScope.launch {
            /*
            try {
                // 1. Register
                val registerResponse = api.register(RegisterRequest("kurva", "kurva@anyad.com", "password", "password"))
                println("Register response: ${registerResponse.isSuccessful}")

                // 2. Login
                val loginResponse = api.login(LoginRequest("kurva@anyad.com", "password"))
                val token1 = loginResponse.body()?.token
                println("Login response: ${loginResponse.isSuccessful}, Token: $token1")

                // 3. Get User (if login was successful)
                if (token1 != null) {
                    try {
                        DataStoreManager.storeUserToken(context, token1)
                        DataStoreManager.getUserTokenFlow(context).collect {
                            token ->
                            val userResponse = api.getUser("Bearer $token")
                            println("User response: ${userResponse.isSuccessful}, Body: ${userResponse.body()}")

                            // 4. Logout
                            val logoutResponse = api.logout("Bearer $token")
                            println("Logout response: ${logoutResponse.isSuccessful}")
                        }

                    }
                    catch (e: Exception) {
                        println("Error storing token: ${e.message}")
                    }
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
*/


            try {
                val response = api.login(LoginRequest(email = _state.value.email, password = _state.value.password))
                println("Login response: ${response.isSuccessful}")

                if (response.isSuccessful) {
                    val loginApiToken = response.body()?.token?: ""
                    DataStoreManager.storeUserToken(context, loginApiToken)
                    DataStoreManager.storeUserEmail(context, _state.value.email)
                    _event.send(LoginUiEvent.LoginSuccessful)
                    println("Login successful")
                } else {
                    println("Login failed: ${response.errorBody()?.string()}")
                    _event.send(LoginUiEvent.LoginFailed(response.errorBody()?.string() ?: "Unknown error"))
                }
            }catch (e: Exception) {
                _event.send(LoginUiEvent.LoginFailed(e.message ?: "Unknown error"))
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    api = WriterReaderApplication.api
                )
            }
        }
    }

}