package hu.bme.aut.android.writer_reader_client.feature.messenger.conversation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.writer_reader_client.R
import hu.bme.aut.android.writer_reader_client.data.model.Message
import hu.bme.aut.android.writer_reader_client.ui.common.Comment
import hu.bme.aut.android.writer_reader_client.ui.common.LikesTracker
import hu.bme.aut.android.writer_reader_client.ui.common.NormalTextField


@Composable
fun ConversationScreen(
    modifier: Modifier = Modifier,
    messages: List<Message>,
    newComment: String = "",
    onSendNewMessage: (String) -> Unit,
    onMessageTextChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        NormalTextField(
            modifier = modifier.fillMaxWidth(),
            value = newComment,
            label = stringResource(id = R.string.textfield_label_comment),
            onValueChange = { onMessageTextChange(it) },
            trailingIcon = {
                IconButton(onClick = { onSendNewMessage}) {
                    Icon(imageVector = Icons.Filled.Send, contentDescription = "Send")
                }
            },
            onDone = { onSendNewMessage }
        )


        LazyColumn(
            modifier = Modifier.weight(weight = 1f, fill = false)
        ) {
            items(messages) { message ->
                MessageItem(message = message)
            }
        }
    }
}




@Composable
fun MessageItem(message: Message, modifier: Modifier = Modifier) {
    Surface(
        modifier
            .fillMaxWidth()
            .padding( horizontal = 2.dp, vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = message.updatedAt,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}


@Preview
@Composable
fun CommentSectionPreview() {
    val comments = listOf(
        Comment(

        ),
        Comment()
    )
    ConversationScreen(
        messages = listOf(
            Message(
                "asd",
                "asd",
                "asd",
                "asd",
                "asd",
                "asd"),
            Message(
                "dsa",
                "dsa",
                "dsa",
                "dsa",
                "dsa",
                "dsa")
        ),
        newComment = "kjshbdf",
        onSendNewMessage = {},
        onMessageTextChange = {}

    )
}