package hu.bme.aut.android.writer_reader_client.ui.common

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WorkCard(
    title: String,
    authorName: String,
    profileImageUrl: String,
    modifier: Modifier = Modifier,
    creationYear: String,
    genre: String,
    characters: List<String>,
    language: String

) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(colorScheme.surface, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Author profile section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(bottom = 8.dp)
        ) {
            AsyncImage(
                model = profileImageUrl,
                contentDescription = "Author Profile Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = modifier.width(8.dp))
            Column {
                Text(
                    text = authorName,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = creationYear,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }

        // Title of the book
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.padding(bottom = 8.dp)
        )

        // Genre
        Text(
            text = "Genre: $genre",
            style = MaterialTheme.typography.bodyMedium,
            color = colorScheme.onSurface.copy(alpha = 0.7f)
        )

        // Characters
        Text(
            text = "Characters: ${characters.joinToString(", ")}",
            style = MaterialTheme.typography.bodyMedium,
            color = colorScheme.onSurface.copy(alpha = 0.7f)
        )

        // Language
        Text(
            text = "Language: $language",
            style = MaterialTheme.typography.bodyMedium,
            color = colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}




@Preview
@Composable
fun TestWorkCard()
{
    WorkCard(
        title = "The Great Gatsby",
        authorName = "F. Scott Fitzgerald",
        profileImageUrl = "https://www.clipartmax.com/png/middle/58-589213_user-profile-avatar-scalable-vector-graphics-icon-profile-girl-avatar.png",
        modifier = Modifier,
        creationYear = "1925",
        genre = "Novel",
        characters = listOf("The Great Gatsby", "Fitzgerald"),
        language = "English"
    )
}