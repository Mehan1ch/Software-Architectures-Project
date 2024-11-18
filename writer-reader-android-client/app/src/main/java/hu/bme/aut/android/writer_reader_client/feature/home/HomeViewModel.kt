package hu.bme.aut.android.writer_reader_client.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(): ViewModel() {


    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()



    init {
        refreshWorks()
    }

    fun refreshWorks(){
        _state.update { it.copy(isLoading = true)}
        viewModelScope.launch(Dispatchers.IO) {
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

    fun loadSelectedPhoto(
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

    }




    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel()
            }
        }
    }
}

data class HomeViewState (
    val isLoading: Boolean = false,
    val works: Flow<PagingData<String>>? = null,
    val searchText: String = "",
    val work: Flow<String>? = null,
    val isError: Boolean = false,
    val throwable: Throwable? = null
)


