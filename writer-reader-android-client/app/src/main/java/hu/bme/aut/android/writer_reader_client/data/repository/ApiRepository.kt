package hu.bme.aut.android.writer_reader_client.data.repository

import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginResponse
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionsResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.UsersResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.WorkResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.WorksResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeResponse
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import retrofit2.Callback
import retrofit2.Response

class ApiRepository(private val api: WriterReaderApi) {

    fun postComment(
        token: String,
        commentRequest: CommentRequest,
        callback: Callback<CommentResponse>
    ) {
        api.postComment("Bearer $token", commentRequest).enqueue(callback)
    }

    fun postLike(
        token: String,
        likeRequest: LikeRequest,
        callback: Callback<LikeResponse>
    ) {
        api.postLike("Bearer $token", likeRequest).enqueue(callback)
    }

    //--------------------------Eddig működöttek---------------------------------

    // Works
    suspend fun getWorks(): Response<WorksResponse> {
        return api.getWorks()
    }

    suspend fun getWork(id: String): Response<WorkResponse> {
        return api.getWork(id)
    }


    // Collection
    suspend fun getCollections(): Response<CollectionsResponse> {
        return api.getCollections()
    }

    suspend fun getCollection(id: String): Response<CollectionResponse> {
        return api.getCollection(id)
    }

  //  suspend fun createCollection(collection: Collection): Response<CollectionResponse> {
  //      return api.createCollection(collection)
  //  }

    // Felhasználók
    suspend fun getUsers(): Response<UsersResponse> {
        return api.getUsers()
    }

    suspend fun getUser(token: String): Response<Any> {
        return api.getUser("Bearer $token")
    }

    // Authentication
    suspend fun register(request: RegisterRequest): Response<Void> {
        return api.register(request)
    }

    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return api.login(request)
    }

    suspend fun logout(token: String): Response<Any> {
        return api.logout("Bearer $token")
    }

}