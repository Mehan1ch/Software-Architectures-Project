package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json

//-----------------------------/api/collections/{id}---------------------------------

data class Collection(
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "creator_id") val creatorId: String,
    @Json(name = "creator_name") val creatorName: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    val likes: Int,
    @Json(name = "is_liked") val isLiked: Boolean,
    val works: WorksData,
    val comments: CommentsData
)

data class CommentsData(
    val data: List<Comment>
)

data class WorksData(
    val data: List<WorksListItem>,
)

data class CollectionResponse(
    val data: Collection
)