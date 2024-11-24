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
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import hu.bme.aut.android.writer_reader_client.data.repository.ApiManager


sealed class LoginViewIntent {
    data class EmailChanged(val email: String) : LoginViewIntent()
    data class PasswordChanged(val password: String) : LoginViewIntent()
    data class LoginButtonClicked(val context: Context, val onSuccessfulLogin: () -> Unit) : LoginViewIntent()
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
    private val api: WriterReaderApi,
    private val apiManager: ApiManager
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
                        DataStoreManager.getUserTokenFlow(context).collect{ token ->
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
                }
            )

/*
                // 1. Register

                apiManager.register(
                    request = RegisterRequest(
                        name = "TestUser",
                        email = "test@example.com",
                        password = "asdasd",
                        passwordConfirmation = "asdasd"
                    ),
                    onSuccess = {
                        println("Register successful: $it")
                    },
                    onError = { errorMessage ->
                        println("Error: $errorMessage")
                    }
                )*/
            try {
                val registerResponse = api.register(RegisterRequest("TestUser", "test@example.com", "password", "password"))
                println("Register response: ${registerResponse.isSuccessful}")

                // 2. Login
                val loginResponse = api.login(LoginRequest("test@example.com", "password"))
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

        }
    }


          /*  try {
                println("Createafsddddddddddddddddddddddddddddddddddddddddddddddddddddddddd like")


                val createLikeResponse = api.createLike(
                    body = LikeRequest(
                        likeableId = "9d8d146a-cd7f-4bbd-81de-8c55378f0e7b",
                        likeableType = "App\\Models\\Work"
                    ),
                    token = "12|dQP7xlgz0z1e5F8n7bTi7j5Hhfhp1qRtZ47Dr2PW8dba9465"

                )
                if (createLikeResponse.isSuccessful) {
                    val responseBody = createLikeResponse.body()?.string()
                    println("Response body: $responseBody")
                } else {
                    println("Create like failed")
                }

            }catch (e: Exception){
                println("Error: ${e.message}")
            }*/


/* //  test comment
                          //  val commentResponse = api.postComment("Bearer $token", CommentRequest(content = "teszt komment", commentableType = "App\\Models\\Work", commentableId = "9d8d146a-cd7f-4bbd-81de-8c55378f0e7b"))
                           // println("Comment response: ${commentResponse.isSuccessful}, Body: ${commentResponse.body()}")
                            /*api.postComment("Bearer $token",CommentRequest(content = "teszt komment", commentableType = "App\\Models\\Work", commentableId = "9d8d146a-cd7f-4bbd-81de-8c55378f0e7b"))
                                .enqueue(object : Callback<CommentResponse> {
                                    override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {

                                        if (response.isSuccessful) {
                                            val commentData = response.body()?.data
                                            println("Comment posted successfully: $commentData")
                                        } else {
                                            println("Error: ${response.errorBody()?.string()}")
                                        }
                                    }

                                    override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                                        println("Failure: ${t.message}")
                                    }

                                })

                            // test like

                            api.postLike("Bearer $token",LikeRequest(likeableType = "App\\Models\\Work", likeableId = "9d8d146b-e7d5-47a0-8f9e-20df01a6614e"))
                                .enqueue(object : Callback<LikeResponse> {
                                    override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {

                                        if (response.isSuccessful) {
                                            val commentData = response.body()?.data
                                            println("Comment posted successfully: $commentData")
                                        } else {
                                            println("Error: ${response.errorBody()?.string()}")
                                        }
                                    }

                                    override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                                        println("Failure: ${t.message}")
                                    }

                                })

*/
                            apiManager.postLike(
                                token = token?:"",
                                likeRequest = LikeRequest(likeableType = "App\\Models\\Work", likeableId = "9d8d146b-e7d5-47a0-8f9e-20df01a6614e"),
                                onSuccess = { likeResponse ->
                                    println("Like posted successfully: ${likeResponse.data}")
                                },
                                onError = { errorMessage ->
                                    println("Error: $errorMessage")
                                }
                            )

            /*
            try {
                val response = api.login(LoginRequest(email = _state.value.email, password = _state.value.password))
                println("Login response: ${response.isSuccessful}")

                if (response.isSuccessful) {
                    val loginApiToken = response.body()?.token?: ""
                    DataStoreManager.storeUserToken(context, loginApiToken)
                    DataStoreManager.storeUserEmail(context, _state.value.email)
                    _event.send(LoginUiEvent.LoginSuccessful)
                    println("Login successful : $loginApiToken")
                } else {
                    println("Login failed: ${response.errorBody()?.string()}")
                    _event.send(LoginUiEvent.LoginFailed(response.errorBody()?.string() ?: "Unknown error"))
                }
            }catch (e: Exception) {
                _event.send(LoginUiEvent.LoginFailed(e.message ?: "Unknown error"))
            }*/

        }
    }*/

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    api = WriterReaderApplication.api,
                    apiManager = WriterReaderApplication.apiManager
                )
            }
        }
    }

}