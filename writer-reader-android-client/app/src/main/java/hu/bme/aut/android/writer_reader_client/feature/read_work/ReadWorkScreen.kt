package hu.bme.aut.android.writer_reader_client.feature.read_work

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.android.writer_reader_client.R
import hu.bme.aut.android.writer_reader_client.feature.work_details.WorkDetailViewModel

@Composable
fun ReadWork(
    modifier: Modifier = Modifier,
    viewModel: WorkDetailViewModel = viewModel(factory = WorkDetailViewModel.Factory),
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { state.work.chapters.size + 1 }
    )
   // val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(vertical = 20.dp)
            .fillMaxSize(),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 0.dp),
        pageSpacing = 10.dp)
    {
        page ->
        Box(modifier = modifier
            .fillMaxSize()
          //  .background(color = Color.Red)
            .padding(horizontal = 10.dp)
        ){
            if (page == 0){
                LazyColumn()
                {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontStyle = Italic,),
                                text = state.work.creatorName
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = Bold,
                                ),
                                text = state.work.title
                            )
                        }
                        Spacer(modifier = modifier.padding(10.dp))
                        Text(
                            style =MaterialTheme.typography.bodyMedium,
                            text = state.work.content,
                            textAlign = TextAlign.Justify
                        )
                    }
                }



            }else{
                val chapter = state.work.chapters[page-1]
                LazyColumn()
                {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                style = MaterialTheme.typography.titleMedium,
                                 text = stringResource(id = R.string.chapter_number, page)
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = Bold,
                                ),
                                text = chapter.title
                            )
                        }
                        Spacer(modifier = modifier.padding(10.dp))
                        Text(
                            style =MaterialTheme.typography.bodyMedium,
                            text = chapter.content,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }

            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }
                    .collect { currentPage ->
                        pagerState.animateScrollToPage(currentPage)
                    }
            }
        }

    }


}

