package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentData
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeData


data class UserDetails(
    val id: String,
    val name: String,
    val email: String,
    @Json(name = "email_verified_at") val emailVerifiedAt: String?,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    val works: NestedData<WorksListItem>,
    val collections: NestedData<CollectionsListItem>,
    val comments: NestedData<CommentData>,
    val likes: List<LikeData>,
    val sentMessages: List<Message>,
    val receivedMessages: List<Message>,
    val moderatedWorks: NestedData<WorksListItem>
)

data class NestedData<T>(
    val data: List<T>
)

