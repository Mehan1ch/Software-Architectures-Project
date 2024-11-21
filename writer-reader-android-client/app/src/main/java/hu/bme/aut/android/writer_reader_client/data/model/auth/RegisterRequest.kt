package hu.bme.aut.android.writer_reader_client.data.model.auth

// RegisterRequest.kt
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String // Laravel expects password confirmation
)