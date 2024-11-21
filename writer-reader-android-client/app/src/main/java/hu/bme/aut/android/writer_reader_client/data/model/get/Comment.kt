package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json


data class Comment(
    val id: String = "",
    val content: String = "",
    @Json(name = "user_id") val user_id: String = "",
   // @Json(name = "user_name") val user_name: String,
    @Json(name = "commantable_type") val commentableType: String = "",
    @Json(name = "commentable_id") val commentableId: String = "",
    @Json(name  = "created_at") val createdAt: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
)