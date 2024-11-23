package hu.bme.aut.android.writer_reader_client.data.model.post

import com.squareup.moshi.Json

data class CommentRequest(
    val content: String,
    @Json(name = "commentable_type") val commentableType: String,
    @Json(name = "commentable_id") val commentableId: String
)

data class CommentResponse(
    val data: CommentData,
)

data class CommentData(
    val id: String,
    val content: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "user_name") val userName: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    val likes: Int,
    @Json(name = "is_liked") val isLiked: Boolean
)