import com.squareup.moshi.Json

data class Work(
    val id: String = "",
    val title: String = "",
    @Json(name = "creator_id") val creatorId: String = "",
    @Json(name = "creator_name") val creatorName: String = "",
    @Json(name  = "created_at") val createdAt: String = "",
    @Json(name = "updated_at") val updatedAt: String = "",
    @Json(name = "moderation_status") val moderationStatus: String = "",
    @Json(name = "moderator_id") val moderatorId: String? = "",
    @Json(name = "moderator_name") val moderatorName: String? = "",
    val language: String = "",
    val rating: String = "",
    val warnings: List<String> = listOf(""),
    val characters: List<String> = listOf(""),
    val tags: List<String> = listOf(""),
    val categories: List<String> = listOf(""),
    val chapters: List<Chapter> = listOf(Chapter()),
    val content: String = "",
    val likes: Int = 0,
    @Json(name = "is_liked") val isLiked: Boolean = false,
    val comments: List<Comment> = listOf(Comment()),
)

data class Chapter(
    val id: String = "",
    val title: String = "",
    val content: String = "",
)

data class WorkResponse(
    val data: Work
)
