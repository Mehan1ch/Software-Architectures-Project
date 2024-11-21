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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val api: WriterReaderApi
): ViewModel() {


    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()



    init {
        refreshWorks()
    }

    fun refreshWorks() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loadedWorks = api.getWorks()
                val works = loadedWorks.body()?.data ?: listOf(WorksListItem())
                _state.update {
                    it.copy(
                        works = works,
                        isLoading = false,
                        isError = false,
                    )
                }

                Log.d("HomeViewModel", "Loaded works: $works")
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        throwable = e
                    )
                }
                Log.d("HomeViewModel", "Error loading works", e)
            }
        }
    }

    fun onSearchTextChange(value: String) {
        _state.update { it.copy(searchText = value) }
        searchWorks(state.value.searchText)
    }

    private fun searchWorks(query: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            try {

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        throwable = e
                    )
                }
            }
        }
    }

/*    fun loadSelectedPhoto(
        photoId: String
    ) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            try {

            } catch (e: Exception) {
                _state.update { it.copy(
                    isLoading = false,
                    isError = true,
                    throwable = e
                ) }
            }
        }


    }*/




    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    api = WriterReaderApplication.api
                )
            }
        }
    }
}

data class HomeViewState(
    val isLoading: Boolean = false,
    val works: List<WorksListItem> = emptyList(),
    val searchText: String = "",
    val isError: Boolean = false,
    val throwable: Throwable? = null
)


