package hu.bme.aut.android.writer_reader_client.data.model.auth

import com.squareup.moshi.Json

// RegisterRequest.kt
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    @Json(name = "password_confirmation") val passwordConfirmation: String
)