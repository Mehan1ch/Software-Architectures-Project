package hu.bme.aut.android.writer_reader_client.data.model.get

import com.squareup.moshi.Json

data class User(
    val id: String,
    val name: String,
    val email: String,
    @Json(name = "email_verified_at: String") val emailVerifiedAt: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)