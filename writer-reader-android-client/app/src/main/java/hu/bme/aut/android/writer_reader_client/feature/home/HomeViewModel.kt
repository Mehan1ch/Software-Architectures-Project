package hu.bme.aut.android.writer_reader_client.feature.home

import android.util.Log
import androidx.compose.animation.core.copy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.get.WorksListItem
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import hu.bme.aut.android.writer_reader_client.data.repository.ApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch




class HomeViewModel(
    private val apiManager: ApiManager
): ViewModel() {


    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()



    init {
        refreshWorks()
    }

    private fun refreshWorks() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            apiManager.getWorks(
                onSuccess = { worksResponse ->
                    _state.update {
                        it.copy(
                            works = worksResponse.data,
                            searchedWorks = worksResponse.data,
                            isLoading = false,
                            isError = false,
                        )
                    }
                    println("Works response: ${worksResponse.data}")
                },
                onError = { errorMessage ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            throwable = Exception(errorMessage)
                        )
                    }
                    Log.e("error","Error: $errorMessage")
                }
            )
        }
    }

    fun onSearchTextChange(value: String) {
        _state.update { it.copy(searchText = value) }
        searchWorks(state.value.searchText)
    }

    private fun searchWorks(query: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val filteredWorks = _state.value.works.filter { work->
                work.title.contains(query, ignoreCase = true) ||
                work.creatorName.contains(query, ignoreCase = true)
            }
            _state.update { it.copy(isLoading = false, searchedWorks = filteredWorks) }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    apiManager = WriterReaderApplication.apiManager
                )
            }
        }
    }
}

data class HomeViewState(
    val isLoading: Boolean = false,
    val works: List<WorksListItem> = emptyList(),
    val searchedWorks: List<WorksListItem> = emptyList(),
    val searchText: String = "",
    val isError: Boolean = false,
    val throwable: Throwable? = null
)


