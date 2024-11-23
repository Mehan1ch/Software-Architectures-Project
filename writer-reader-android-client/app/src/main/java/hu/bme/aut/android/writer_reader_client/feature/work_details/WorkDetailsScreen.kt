package hu.bme.aut.android.writer_reader_client.feature.work_details

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Button
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
import hu.bme.aut.android.writer_reader_client.navigation.Screen
import hu.bme.aut.android.writer_reader_client.ui.common.CommentSection
import hu.bme.aut.android.writer_reader_client.ui.common.NormalTextField
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.text.format


@Composable
fun WorkDetailsScreen(
    modifier: Modifier = Modifier,
  //  onNavigateBack: () -> Unit,
    viewModel: WorkDetailViewModel = viewModel(factory = WorkDetailViewModel.Factory),
    onNavigateToReadWork: (String) -> Unit
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
                    title = state.work.title,
                    author = state.work.creatorName,
                    date = state.work.createdAt,
                    categories = state.work.categories,
                    tags = state.work.tags,
                    isLiked = state.isLiked,
                    onLikeClick = {
                        viewModel.onIntent(WorkDetailViewIntent.LikeWorkButtonClicked)
                    },
                    onReadButtonClick = {
                        onNavigateToReadWork(state.work.id)
                    }
                )

            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.weight(1f)) {
                CommentSection(
                    comments = state.work.comments,
                    newComment = state.newComment,
                    onCommentChange = {newValue ->
                        viewModel.onIntent(WorkDetailViewIntent.CommentChanged(newValue))
                    },
                    onSendNewComment = {
                        viewModel.onIntent(WorkDetailViewIntent.CommentSendButtonClicked)
                    },
                    onCommentLike = {
                        viewModel.onIntent(WorkDetailViewIntent.LikeCommentButtonClicked(it))
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
    categories: List<String>,
    tags: List<String>,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onReadButtonClick: () -> Unit
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
        FlowRow() {
            categories.forEach { category ->
                Chip(onClick = {}) {
                    Text(text = category)
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        FlowRow() {
            tags.forEach { tag ->
                Chip(onClick = {}) {
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
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onReadButtonClick() },
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = stringResource(id = R.string.button_read_work))
        }
    }
}

@Preview
@Composable
fun WorkDetailScreenPreview() {
    WorkDetailsScreen(
        onNavigateToReadWork = {},
    )
}


