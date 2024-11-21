package hu.bme.aut.android.writer_reader_client.data.model

import com.squareup.moshi.Json
/*


data class Collection(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    @Json(name = "creator_id") val creatorId: String = "",
    @Json(name = "creator_name") val creatorName: String = "",
    @Json(name = "created_at") val createdAt: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
    val works: List<WorksListItem> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val likes: Int = 0
)

data class CollectionsListItem(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    @Json(name = "creator_id") val userId: String = "",
    @Json(name = "creator_name") val creatorName: String = "",
    @Json(name = "number_of_works") val numberOfWorks: Int = 0,
    val likes: Int = 0,
//    @Json(name = "is_liked") val isLiked: Boolean = false,
)

data class WorksListItem(
    val id: String = "",
    val title: String = "",
    @Json(name = "creator_name") val creatorName: String = "",
    @Json(name = "creator_id") val creatorId: String = "",
    @Json(name = "created_at") val createdAt: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
    val language: String = "",
    val category: String = "",
    val likes: Int = 0,
)


data class Work(
    val id: String = "",
    val title: String = "",
    @Json(name = "creator_name") val creatorName: String = "",
    @Json(name = "creator_id") val creatorId: String = "",
    @Json(name  = "created_at") val createdAt: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",

    val language: String = "",
    val category: String = "",
    val rating: String = "",
    val characters: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val likes: Int = 0,

    val chapters: List<Chapter> = emptyList(),
    val content: String = "",
    val comments: List<Comment> = emptyList(),
)

data class Chapter(
    val id: String = "",
    val title: String = "",
    val content: String = "",
)

data class Comment(
    val id: String = "",
    val content: String = "",
    @Json(name = "user_id") val userId: String = "",
    @Json(name = "user_name") val userName: String = "",
    @Json(name = "created_at") val createdAt: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
    val likes: Int = 0,
    val isLiked: Boolean = false,
)

*/