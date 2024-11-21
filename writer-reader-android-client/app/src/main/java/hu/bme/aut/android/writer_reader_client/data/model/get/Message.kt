package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json

data class Message(
    val id: String,
    val content: String,
    @Json(name = "sent_by_id") val sentById: String,
    @Json(name = "sent_to_id") val sentToId: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)