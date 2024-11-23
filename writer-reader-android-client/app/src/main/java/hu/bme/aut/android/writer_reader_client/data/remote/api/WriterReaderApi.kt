package hu.bme.aut.android.writer_reader_client.data.remote.api


import hu.bme.aut.android.writer_reader_client.data.model.auth.ForgotPasswordRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.ForgotPasswordResponse
import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.LoginResponse
import hu.bme.aut.android.writer_reader_client.data.model.auth.RegisterRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.ResetPasswordRequest
import hu.bme.aut.android.writer_reader_client.data.model.auth.ResetPasswordResponse
import hu.bme.aut.android.writer_reader_client.data.model.auth.VerificationNotificationResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.Collection
import hu.bme.aut.android.writer_reader_client.data.model.get.Comment
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.CollectionsResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.User
import hu.bme.aut.android.writer_reader_client.data.model.get.WorkResponse
import hu.bme.aut.android.writer_reader_client.data.model.get.WorksResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
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
    @GET("api/collections")
    suspend fun getCollections(): Response<CollectionsResponse>

    @GET("api/collections/{id}")
    suspend fun getCollection(@Path("id") id: String): Response<CollectionResponse>

    @POST("api/collections")
    suspend fun createCollection(@Body collection: Collection): Response<Collection>

    @PUT("api/collections/{id}")
    suspend fun updateCollection(@Path("id") id: String, @Body collection: Collection): Response<Collection>

    @DELETE("api/collections/{id}")
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

     // Chapters
     @GET("/chapters")
     suspend fun getChapters(): Response<List<Chapter>>

     @GET("/chapters/{id}")
     suspend fun getChapter(@Path("id") id: String): Response<Chapter>

     @POST("/chapters")
     suspend fun createChapter(@Body chapter: Chapter): Response<Chapter>

     @PUT("/chapters/{id}")
     suspend fun updateChapter(@Path("id") id: String, @Body chapter: Chapter): Response<Chapter>

     @DELETE("/chapters/{id}")
     suspend fun deleteChapter(@Path("id") id: String): Response<Void>

     // Characters
     @GET("/characters")
     suspend fun getCharacters(): Response<List<Character>>

     @GET("/characters/{id}")
     suspend fun getCharacter(@Path("id") id: String): Response<Character>

     @POST("/characters")
     suspend fun createCharacter(@Body character: Character): Response<Character>

     @PUT("/characters/{id}")
     suspend fun updateCharacter(@Path("id") id: String, @Body character: Character): Response<Character>

     @DELETE("/characters/{id}")
     suspend fun deleteCharacter(@Path("id") id: String): Response<Void>
 */

    // Comments
    @GET("api/comments")
    suspend fun getComments(): Response<List<Comment>>

    @GET("api/comments/{id}")
    suspend fun getComment(@Path("id") id: String): Response<Comment>

    @POST("api/comments")
    suspend fun createComment(@Body comment: Comment): Response<Comment>

    @PUT("api/comments/{id}")
    suspend fun updateComment(@Path("id") id: String, @Body comment: Comment): Response<Comment>

    @DELETE("api/comments/{id}")
    suspend fun deleteComment(@Path("id") id: String): Response<Void>
/*
    // Languages
    @GET("/languages")
    suspend fun getLanguages(): Response<List<Language>>

    @GET("/languages/{id}")
    suspend fun getLanguage(@Path("id") id: String): Response<Language>
*/ /*
    // Likes
    @GET("/likes")
    suspend fun getLikes(): Response<List<Like>>

    @GET("/likes/{id}")
    suspend fun getLike(@Path("id") id: String): Response<Like>

    @POST("/likes")
    suspend fun createLike(@Body like: Like): Response<Like>

    @PUT("/likes/{id}")
    suspend fun updateLike(@Path("id") id: String, @Body like: Like): Response<Like>

    @DELETE("/likes/{id}")
    suspend fun deleteLike(@Path("id") id: String): Response<Void>

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
/*
    // Ratings
    @GET("/ratings")
    suspend fun getRatings(): Response<List<Rating>>

    @GET("/ratings/{id}")
    suspend fun getRating(@Path("id") id: String): Response<Rating>

    @POST("/ratings")
    suspend fun createRating(@Body rating: Rating): Response<Rating>

    @PUT("/ratings/{id}")
    suspend fun updateRating(@Path("id") id: String, @Body rating: Rating): Response<Rating>

    @DELETE("/ratings/{id}")
    suspend fun deleteRating(@Path("id") id: String): Response<Void>

    // Tags
    @GET("/tags")
    suspend fun getTags(): Response<List<Tag>>

    @GET("/tags/{id}")
    suspend fun getTag(@Path("id") id: String): Response<Tag>

    @POST("/tags")
    suspend fun createTag(@Body tag: Tag): Response<Tag>

    @PUT("/tags/{id}")
    suspend fun updateTag(@Path("id") id: String, @Body tag: Tag): Response<Tag>

    @DELETE("/tags/{id}")
    suspend fun deleteTag(@Path("id") id: String): Response<Void>
*/

    // Users
    @GET("api/users")
    suspend fun getUsers(): Response<List<User>>

  //  @GET("api/users/{id}")
  // suspend fun getUser(@Path("id") id: String): Response<User>

    @POST("api/users")
    suspend fun createUser(@Body user: User): Response<User>

    @PUT("api/users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): Response<User>

    @DELETE("api/users/{id}")
    suspend fun deleteUser(@Path("id") id: String): Response<Void>



/*
    // Warnings
    @GET("/warnings")
    suspend fun getWarnings(): Response<List<Warning>>

    @GET("/warnings/{id}")
    suspend fun getWarning(@Path("id") id: String): Response<Warning>

    @POST("/warnings")
    suspend fun createWarning(@Body warning: Warning): Response<Warning>

    @PUT("/warnings/{id}")
    suspend fun updateWarning(@Path("id") id: String, @Body warning: Warning): Response<Warning>

    @DELETE("/warnings/{id}")
    suspend fun deleteWarning(@Path("id") id: String): Response<Void>
*/

    // Authentication
    @POST("/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void?>

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("api/user")
    suspend fun getUser(@Header("Authorization") authHeader: String): Response<Any>

    @POST("api/logout")
    suspend fun logout(@Header("Authorization") authHeader: String): Response<Any>

   // @GET("/verify-email/{id}/{hash}")
  //  suspend fun verifyEmail(@Path("id") id: String, @Path("hash") hash: String): Response<VerifyEmailResponse>

  //  @POST("/email/verification-notification")
    //suspend fun sendVerificationNotification(): Response<VerificationNotificationResponse>

   // @POST("/logout")
   // suspend fun logout(): Response<LogoutResponse>


    /*
    @POST("/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<ForgotPasswordResponse>

    @POST("/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ResetPasswordResponse>
*/
}


