package hu.bme.aut.android.writer_reader_client.data.repository

import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginResponse
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionsResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.UserDetails
import hu.bme.aut.android.writer_reader_client.data.model.get.UsersResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.WorkResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.WorksResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiManager(private val repository: ApiRepository) {

    fun postComment(
        token: String,
        commentRequest: CommentRequest,
        onSuccess: (CommentResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.postComment(token, commentRequest, object : Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError("Response body is null")
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                onError(t.message ?: "Network error")
            }
        })
    }

    fun postLike(
        token: String,
        likeRequest: LikeRequest,
        onSuccess: (LikeResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        repository.postLike(token, likeRequest, object : Callback<LikeResponse> {
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError("Response body is null")
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                onError(t.message ?: "Network error")
            }
        })
    }


    //---------------------------------------------Eddig működött----------------------------------------

    // Works
    suspend fun getWorks(
        onSuccess: (WorksResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = repository.getWorks()
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }

    suspend fun getWork(
        id: String,
        onSuccess: (WorkResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = repository.getWork(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }

    //Users
    suspend fun getUsers(
        onSuccess: (UsersResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = repository.getUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }



    // Collections
    suspend fun getCollections(
        onSuccess: (CollectionsResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = repository.getCollections()
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }

    suspend fun getCollection(
        id: String,
        onSuccess: (CollectionResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = repository.getCollection(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }


    // Authentication
    suspend fun register(
       request: RegisterRequest,
       onSuccess: (Response<Void?>) -> Unit,
       onError: (String) -> Unit
    ) {
        try {
            val response = repository.register(request)
            if (response.isSuccessful) {
                onSuccess(response)
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }

    suspend fun login(
        request: LoginRequest,
        onSuccess: (LoginResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = repository.login(request)
            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    if (loginResponse.token.isNullOrEmpty()) {
                        onError("Token is null or empty")
                    }else {
                        onSuccess(loginResponse)
                    }
                } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }

    suspend fun logout(
        token: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val response = repository.logout(token)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }

    //Users
    suspend fun getUserById(
        id: String,
        onSuccess: (UserDetails) -> Unit,
        onError: (String) -> Unit
    ){
        try {
            val response = repository.getUserById(id)
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    onSuccess(responseBody)
                    } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }

    suspend fun getCurrentUser(
        token: String,
        onSuccess: (Any) -> Unit,
        onError: (String) -> Unit
    ){
        try {
            val response = repository.getUser(token)
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    onSuccess(responseBody as UserDetails)
                    } ?: onError("Response body is null")
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            onError(e.message ?: "Network error")
        }
    }
}