package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json

//-----------------------------/api/collections--------------------------------

data class CollectionsListItem(
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "creator_id") val creatorId: String,
    @Json(name = "creator_name") val creatorName: String,
    @Json(name = "number_of_works") val numberOfWorks: Int,
    val likes: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)

data class CollectionsResponse(
    val data: List<CollectionsListItem>,
    val links: Links,
    val meta: Meta
)