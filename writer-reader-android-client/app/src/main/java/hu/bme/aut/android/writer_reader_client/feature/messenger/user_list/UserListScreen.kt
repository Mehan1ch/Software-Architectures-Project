package hu.bme.aut.android.writer_reader_client.feature.messenger.user_list
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import hu.bme.aut.android.writer_reader_client.navigation.Screen
import hu.bme.aut.android.writer_reader_client.ui.common.NavigationBottomAppBar
import hu.bme.aut.android.writer_reader_client.ui.common.SearchTopAppBar
import hu.bme.aut.android.writer_reader_client.ui.common.WorkCard




@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@ExperimentalPagingApi
@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: UserListViewModel = viewModel(factory = UserListViewModel.Factory)
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SearchTopAppBar(
                modifier = modifier.windowInsetsPadding(WindowInsets.statusBars),
                text = state.searchText,
                onTextChange = viewModel::onSearchTextChange,
            )
        },
        bottomBar = { NavigationBottomAppBar(navHostController) },
    ) {
        Box(modifier = modifier.padding(it)) {
            LazyColumn(
            ) {
                items(state.users.size) { index ->
                    val user = state.users[index]
                    ListItem(
                        headlineContent = { Text(text = user.name) },
                        supportingContent = { Text(text = user.email) },
                        modifier = modifier
                            .clickable {
                                navHostController.navigate(Screen.Conversation.passUserId(user.id))
                            }
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
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