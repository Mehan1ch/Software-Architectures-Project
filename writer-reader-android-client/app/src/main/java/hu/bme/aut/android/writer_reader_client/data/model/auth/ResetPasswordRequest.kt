package hu.bme.aut.android.writer_reader_client.data.model.auth

data class ResetPasswordRequest(
    val token: String,
    val email: String,
    val password: String,
    val password_confirmation: String
)