package hu.bme.aut.android.writer_reader_client.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.writer_reader_client.R
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
            NormalTextField(
                modifier = modifier
                    .fillMaxWidth(),
                value = newComment,
                label = stringResource(id = R.string.textfield_label_comment),
                onValueChange = {
                    onCommentChange(it)
                },
                trailingIcon = {
                    IconButton(onClick = { onSendNewComment() }) {
                        Icon(imageVector = Icons.Filled.Send, contentDescription = "Send")
                    }
                },
                onDone = { onSendNewComment() }
            )
            LazyColumn(
                modifier = Modifier.weight(weight = 1f, fill = false)
            ) {
                items(comments) { comment ->
                    CommentItem(comment = comment, modifier = modifier, onCommentLike = onCommentLike)
                }
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
                Text(
                    text = comment.updatedAt,
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