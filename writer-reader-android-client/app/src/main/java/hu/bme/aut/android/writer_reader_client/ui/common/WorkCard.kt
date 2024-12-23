package hu.bme.aut.android.writer_reader_client.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.writer_reader_client.R
import hu.bme.aut.android.writer_reader_client.data.model.convertStringToMyTimestamp

@Composable
fun WorkCard(
    title: String,
    authorName: String,
    modifier: Modifier = Modifier,
    creationYear: String,
    category: List<String>,
    language: String,
    likes: Int = 0
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
                    text = convertStringToMyTimestamp(creationYear).year,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = modifier.padding(bottom = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 2.dp),
                thickness = 0.5.dp, // Thinner line
                color = colorScheme.onSurface.copy(alpha = 0.12f) // Light gray color
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(bottom = 8.dp)
            ){
                Column(
                    modifier = modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${stringResource(id = R.string.string_category)} : ${category.joinToString(", ")}",
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
                LikesTracker(likes = likes, modifier = modifier.align(Alignment.Bottom))
            }

        }
    }
}


@Preview
@Composable
fun TestWorkCard()
{
    WorkCard(
        title = "The Great Gatsby",
        authorName = "F. Scott Fitzgerald",
        modifier = Modifier,
        creationYear = "1925",
        category = listOf("Regény","asdfasd"),
        language = "Magyar"
    )
}