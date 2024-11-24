package hu.bme.aut.android.writer_reader_client.ui.common


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import hu.bme.aut.android.writer_reader_client.data.model.convertStringToMyTimestamp
import hu.bme.aut.android.writer_reader_client.data.model.get.Comment


@Composable
fun CommentSection(
    modifier: Modifier = Modifier,
    comments: List<Comment>,
    newComment: String = "",
    onSendNewComment: () -> Unit,
    onCommentLike: (String) -> Unit,
    onCommentChange: (String) -> Unit
) {

    Box(modifier = modifier) {
        Column(){
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = newComment,
                label = { Text(text = stringResource(id = R.string.textfield_label_comment)) },
                onValueChange = { newValue ->
                    onCommentChange(newValue)
                },
                trailingIcon ={
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = null,
                        modifier = modifier.clickable { onSendNewComment() }
                    )
                }
            )

            comments.reversed().forEach { comment ->
                CommentItem(comment = comment, modifier = modifier, onCommentLike = onCommentLike)
            }
        }
    }
}





@Composable
fun CommentItem(comment: Comment, modifier: Modifier = Modifier, onCommentLike: (String) -> Unit) {
    Surface(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 6.dp),
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
                    text = comment.userName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = modifier.width(4.dp))
                val date = convertStringToMyTimestamp(comment.createdAt)
                Text(
                    text = "${date.year}.${date.month}.${date.day}. ${date.hour}:${date.minute}",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }

            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = comment.content,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.height(2.dp))
            LikesTracker(likes = comment.likes, isLiked = comment.isLiked, modifier = modifier
                .align(Alignment.End)
                .clickable { onCommentLike(comment.id) })
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
    CommentSection(comments = comments, onSendNewComment = {}, onCommentChange = {}, onCommentLike = {})
}