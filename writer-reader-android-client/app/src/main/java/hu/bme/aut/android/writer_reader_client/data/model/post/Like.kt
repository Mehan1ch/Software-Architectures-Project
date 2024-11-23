package hu.bme.aut.android.writer_reader_client.data.model.post

import com.squareup.moshi.Json

data class LikeRequest(
    @Json(name = "likeable_id") val likeableId: String,
    @Json(name = "likeable_type") val likeableType: String
)

data class LikeResponse(
    val data: LikeData
)

data class LikeData(
    val id: String,
    val user: LikeUser,
    @Json(name = "likeable_id") val likeableId: String,
    @Json(name = "likeable_type") val likeableType: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class LikeUser(
    val id: Int,
    val name: String,

)