package hu.bme.aut.android.writer_reader_client.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hu.bme.aut.android.writer_reader_client.R

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
    Box(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
            .background(colorScheme.surface, RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = colorScheme.onSurface.copy(alpha = 0.12f),
                shape = RoundedCornerShape(8.dp)
            )
    ){
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(colorScheme.surface, RoundedCornerShape(8.dp))
        ) {
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
            // Title of the book
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(bottom = 8.dp)
            ){
                Column(
                    modifier = modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Genre
                    Text(
                        text = "${stringResource(id = R.string.string_genre)}  : $genre",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    // Language
                    Text(
                        text = "${stringResource(id = R.string.string_language)}  : $language",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
                LikesTracker(likes = 5, modifier = modifier.align(Alignment.Bottom))
            }

        }
    }
}




@Composable
fun WorkCard2(
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
            Spacer(modifier = modifier.weight(1f))
            LikesTracker(likes = 5)
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
        LikesTracker(likes = 5, modifier = modifier.align(Alignment.End))
    }
}

@Composable
private fun LikesTracker(
    likes: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(5.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ThumbUp,
            contentDescription = null,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 5.dp)
        )
        Text(
            text = "$likes",
            modifier = Modifier.padding(5.dp),
        )
    }
}

@Preview
@Composable
fun LikesTracker_Preview() {
    LikesTracker(likes = 5)
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
        genre = "Regény",
        characters = listOf("The Great Gatsby", "Fitzgerald"),
        language = "Magyar"
    )
}