
package hu.bme.aut.android.writer_reader_client.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator

import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import hu.bme.aut.android.writer_reader_client.navigation.Screen
import hu.bme.aut.android.writer_reader_client.ui.common.NavigationBottomAppBar
import hu.bme.aut.android.writer_reader_client.ui.common.SearchTopAppBar
import hu.bme.aut.android.writer_reader_client.ui.common.WorkCard




@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize().windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            SearchTopAppBar(
                text = state.searchText,
                onTextChange = viewModel::onSearchTextChange,
            )
        },
        bottomBar = { NavigationBottomAppBar(navHostController) },
    ) {
        Box(modifier = modifier.padding(it)) {
            if (state.isLoading) {
                // Töltő animáció
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                LazyColumn(
                ) {
                    items(state.searchedWorks.size) { index ->
                        val work = state.searchedWorks[index]
                        Box(modifier.clickable {
                            navHostController.navigate(Screen.WorkDetails.passWorkId(work.id))
                        }) {
                            WorkCard(
                                title = work.title,
                                authorName = work.creatorName,
                                creationYear = work.createdAt,
                                category = work.category,
                                language = work.language,
                                likes = work.likes,
                            )
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenPreview() {
  //  HomeScreen(navHostController = NavHostController(LocalContext.current))
}