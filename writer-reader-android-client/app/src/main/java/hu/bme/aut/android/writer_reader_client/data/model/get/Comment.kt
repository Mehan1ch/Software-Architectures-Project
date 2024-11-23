package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json


data class Comment(
    val id: String = "",
    val content: String = "",
    @Json(name = "user_id") val userId: String = "",
    @Json(name = "user_name") val userName: String = "",
    @Json(name = "commentable_type") val commentableType: String = "",
    @Json(name = "commentable_id") val commentableId: String = "",
    @Json(name  = "created_at") val createdAt: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
    val likes: Int = 0,
    @Json(name = "is_liked") val isLiked: Boolean = false,
)