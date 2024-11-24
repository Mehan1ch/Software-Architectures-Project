package hu.bme.aut.android.writer_reader_client.feature.work_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Chip
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.android.writer_reader_client.R
import hu.bme.aut.android.writer_reader_client.data.model.convertStringToMyTimestamp
import hu.bme.aut.android.writer_reader_client.data.model.get.Work
import hu.bme.aut.android.writer_reader_client.ui.common.CommentSection
import hu.bme.aut.android.writer_reader_client.ui.common.LikesTracker


@Composable
fun WorkDetailsScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: WorkDetailViewModel = viewModel(factory = WorkDetailViewModel.Factory(context = LocalContext.current, onNavigateToLogin)),
    onNavigateToReadWork: (String) -> Unit,
){
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.work_details_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = "navigate back")
                    }
                },
                backgroundColor = Color.LightGray
            )
       }
    ) {  paddingValues ->
        Box() {
            if (state.isLoading) {
                // Töltő animáció
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)), // Átlátszó fekete háttér
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    item {
                        Details(
                            work = state.work,
                            onLikeClick = {
                                viewModel.onIntent(WorkDetailViewIntent.LikeWorkButtonClicked)

                            },
                            onReadButtonClick = {
                                onNavigateToReadWork(state.work.id)
                                println("Read button clicked")
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
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
    }
}


@Composable
fun Details(
    modifier: Modifier = Modifier,
    work: Work,
    onLikeClick: () -> Unit,
    onReadButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = work.title, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(8.dp))
        val date = convertStringToMyTimestamp(work.createdAt)
        Text(text = "${date.year}.${date.month}.${date.day}.", style = MaterialTheme.typography.bodyMedium)

        DetailItem(
            label = stringResource(id = R.string.author),
            value = work.creatorName
        )
        if (work.chapters.isNotEmpty()){
            DetailItem(
                label = stringResource(id = R.string.number_of_chapters),
                value = work.chapters.size.toString()
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        DetailItem(
            label = stringResource(id = R.string.language),
            value = work.language
        )

        DetailItem(
            label = stringResource(id = R.string.age_rating),
            value = work.rating
        )

        // Figyelmeztetések
        if (work.warnings.isNotEmpty() && work.warnings.any { it.isNotBlank() }) {
            DetailGroup(
                title = stringResource(id = R.string.warnings),
                items = work.warnings
            )
        }

        // Karakterek
        if (work.characters.isNotEmpty() && work.characters.any { it.isNotBlank() }) {
            DetailGroup(
                title = stringResource(id = R.string.characters),
                items = work.characters
            )
        }

        // Címkék
        if (work.tags.isNotEmpty() && work.tags.any { it.isNotBlank() }) {
            DetailGroup(
                title = stringResource(id = R.string.tags),
                items = work.tags
            )
        }

        // Kategóriák
        if (work.categories.isNotEmpty() && work.categories.any { it.isNotBlank() }) {
            DetailGroup(
                title = stringResource(id = R.string.categories),
                items = work.categories
            )
        }

        // Interaktív elemek
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            LikesTracker(
                likes = work.likes,
                isLiked = work.isLiked,
                modifier = Modifier.clickable { onLikeClick() }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onReadButtonClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.button_read_work))
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun DetailGroup(title: String, items: List<String>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = "$title:",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
        items.filter { it.isNotBlank() }.forEach { item ->
            Text(
                text = "- $item",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp, top = 2.dp)
            )
        }
    }
}




@Preview
@Composable
fun WorkDetailScreenPreview() {
    WorkDetailsScreen(
        onNavigateToReadWork = {},
        onNavigateBack = {},
        onNavigateToLogin = {}
    )
}


