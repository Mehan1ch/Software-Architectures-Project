package hu.bme.aut.android.writer_reader_client.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LikesTracker(
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
