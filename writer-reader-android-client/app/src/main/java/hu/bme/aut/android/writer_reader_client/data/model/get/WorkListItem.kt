package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json

data class Links(
    val first: String,
    val last: String,
    val prev: String?,
    val next: String?
)
data class Meta(
    @Json(name = "current_page") val currentPage: Int,
    val from: Int,
    @Json(name = "last_page") val lastPage: Int,
    val links: List<Link>,
    val path: String,
    @Json(name = "per_page") val perPage: Int,
    val to: Int,
    val total: Int
)
data class Link(
    val url: String?,
    val label: String,
    val active: Boolean
)

//-----------------------------/api/works-------------------------------
data class WorksListItem(
    val id: String,
    val title: String,
    @Json(name = "creator_id") val creatorId: String,
    @Json(name = "creator_name") val creatorName: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    val language: String,
    val category: List<String>,
    val likes: Int
)
data class WorksResponse(
    val data: List<WorksListItem>,
    val links: Links,
    val meta: Meta
)

/*

data class Category (
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)


data class Character (
    val id: String,
    val name: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class Collection (
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)


data class Language (
    val id: String,
    val name: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class Like (
    val id: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "likeable_type") val likeableType: String,
    @Json(name = "likeable_id") val likeableId: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class Message(
    val id: String,
    val content: String,
    @Json(name = "sent_by_id") val sentById: String,
    @Json(name = "sent_to_id") val sentToId: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class Rating(
    val id: String,
    val details: String,
    @Json(name="created_at") val createdAt: String,
    @Json(name="updated_at") val updatedAt: String
)

data class Tag(
    val id: String,
    val name: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    @Json(name = "email_verified_at: String") val emailVerifiedAt: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class Warning(
    val id: String,
    val details: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String

)*/