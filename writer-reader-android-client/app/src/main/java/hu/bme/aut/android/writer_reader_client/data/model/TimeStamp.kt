package hu.bme.aut.android.writer_reader_client.data.model


data class MyTimestamp(
    val year: String,
    val month: String,
    val day: String,
    val hour: String,
    val minute: String
)

fun convertStringToMyTimestamp(dateString: String): MyTimestamp {
    val (datePart, timePart) = dateString.split("T")
    val (year, month, day) = datePart.split("-")
    val (hour, minute, _) = timePart.split(":", ".").take(3) // Ignore seconds and milliseconds

    return MyTimestamp(year, month, day, hour, minute)
}