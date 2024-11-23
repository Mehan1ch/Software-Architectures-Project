package hu.bme.aut.android.writer_reader_client.data.remote.api


import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginResponse
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.model.get.Collection
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionsResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.Comment
import hu.bme.aut.android.writer_reader_client.data.model.get.User
import hu.bme.aut.android.writer_reader_client.data.model.get.WorkResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.WorksResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentResponse
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WriterReaderApi {
    //WORKS
    @GET("/api/works")
    suspend fun getWorks(): Response<WorksResponse>

    @GET("/api/works/{id}")
    suspend fun getWork(@Path("id") id: String): Response<WorkResponse>

    // Collections
    @GET("/api/collections")
    suspend fun getCollections(): Response<CollectionsResponse>

    @GET("/api/collections/{id}")
    suspend fun getCollection(@Path("id") id: String): Response<CollectionResponse>

    @POST("/api/collections")
    suspend fun createCollection(@Body collection: Collection): Response<Collection>

    @PUT("/api/collections/{id}")
    suspend fun updateCollection(@Path("id") id: String, @Body collection: Collection): Response<Collection>

    @DELETE("/api/collections/{id}")
    suspend fun deleteCollection(@Path("id") id: String): Response<Void>




    /*
     // Categories
     @GET("/categories")
     suspend fun getCategories(): Response<List<Category>>

     @GET("/categories/{id}")
     suspend fun getCategory(@Path("id") id: String): Response<Category>

     @POST("/categories")
     suspend fun createCategory(@Body category: Category): Response<Category>

     @PUT("/categories/{id}")
     suspend fun updateCategory(@Path("id") id: String, @Body category: Category): Response<Category>

     @DELETE("/categories/{id}")
     suspend fun deleteCategory(@Path("id") id: String): Response<Void>



 */
    // Comments
    @GET("/api/comments")
    suspend fun getComments(): Response<List<Comment>>

    @POST("/api/comments")
    fun postComment(
        @Header("Authorization") authHeader: String,
        @Body comment: CommentRequest
    ): Call<CommentResponse>


    @POST("/api/likes")
    fun postLike(
        @Header("Authorization") authHeader: String,
        @Body body: LikeRequest
        ): Call<Any>


    @DELETE("/api/likes/{id}")
    fun deleteLike(
        @Path("id") likeId: String,
        @Header("Authorization") token: String
    ): Response<Void>
/*
    // Messages
    @GET("/messages")
    suspend fun getMessages(): Response<List<Message>>

    @GET("/messages/{id}")
    suspend fun getMessage(@Path("id") id: String): Response<Message>

    @POST("/messages")
    suspend fun createMessage(@Body message: Message): Response<Message>

    @PUT("/messages/{id}")
    suspend fun updateMessage(@Path("id") id: String, @Body message: Message): Response<Message>

    @DELETE("/messages/{id}")
    suspend fun deleteMessage(@Path("id") id: String): Response<Void>

    */

    // Users
    @GET("/api/users")
    suspend fun getUsers(): Response<List<User>>



    // Authentication
    @POST("/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void?>

    @POST("/api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("/api/user")
    suspend fun getUser(@Header("Authorization") authHeader: String): Response<Any>

    @POST("/api/logout")
    suspend fun logout(@Header("Authorization") authHeader: String): Response<Any>

}


