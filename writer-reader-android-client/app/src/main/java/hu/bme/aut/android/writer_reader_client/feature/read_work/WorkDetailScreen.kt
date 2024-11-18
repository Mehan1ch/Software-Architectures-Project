package hu.bme.aut.android.writer_reader_client.feature.read_work

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.android.writer_reader_client.R
import hu.bme.aut.android.writer_reader_client.feature.login.LoginViewModel
import hu.bme.aut.android.writer_reader_client.ui.common.NormalTextField




@Composable
fun WorkDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: WorkDetailViewModel = viewModel(factory = WorkDetailViewModel.Factory),
){
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            //TODO
       }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(modifier = Modifier.weight(1f)){
                Details(
                    title = state.title,
                    author = state.author,
                    date = state.date,
                    category = state.category,
                    tags = state.tags,
                    isLiked = state.isLiked,
                    onLikeClick = {
                        //TODO
                    }
                )

            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                CommentSection(
                    comments = state.comments,
                    newComment = state.newComment,
                    onSendNewComment = {
                        //TODO
                    }
                )
            }

        }
    }
}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun Details(
    modifier: Modifier = Modifier,
    title: String,
    author: String,
    date: String,
    category: String,
    tags: List<String>,
    isLiked: Boolean,
    onLikeClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$author • $date",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        FlowRow() {
            tags.forEach { tag ->
                Chip(onClick = { /*TODO*/ }) {
                    Text(text = tag)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (isLiked) "Unlike" else "Like",
                modifier = Modifier.clickable { onLikeClick() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (isLiked) "Liked" else "Like")
        }
    }
}
/*

@Composable
fun MarkdownText(
    text: String,
    modifier: Modifier = Modifier
) {
    val markdown = remember { Markdown() }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = { textView ->
            textView.text = HtmlCompat.fromHtml(markdown.parse(text), HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    )
}
*/
data class Comment(
    val author: String,
    val timestamp: String,
    val text: String
)

@Composable
fun CommentSection(
    modifier: Modifier = Modifier,
    comments: List<Comment>,
    newComment: String = "",
    onSendNewComment: (String) -> Unit
) {
    Column(modifier = modifier) {
        NormalTextField(
            modifier = modifier.fillMaxWidth(),
            value = newComment,
            label = stringResource(id = R.string.textfield_label_comment),
            onValueChange = { /*TODO*/ },
            trailingIcon = {
                IconButton(onClick = { onSendNewComment }) {
                    Icon(imageVector = Icons.Filled.Send, contentDescription = "Send")
                }
            },
            onDone = { onSendNewComment }

        )


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(comments) { comment ->
                CommentItem(
                    author = comment.author,
                    timestamp = comment.timestamp,
                    text = comment.text
                )
            }
        }
    }
}




@Composable
fun CommentItem(author: String, timestamp: String, text: String, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = author,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = timestamp,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun WorkDetailScreenPreview() {
    WorkDetailScreen()
}


@Preview
@Composable
fun CommentSectionPreview() {
    val comments = listOf(
        Comment(
            author = "John Doe",
            timestamp = "2021. ápr. 01. 12:00",
            text = "This is a comment."
        ),
        Comment(
            author = "John Doe",
            timestamp = "2021. ápr. 01. 12:00",
            text = "This is a comment."
        )
    )
    CommentSection(comments = comments, onSendNewComment = {})






}