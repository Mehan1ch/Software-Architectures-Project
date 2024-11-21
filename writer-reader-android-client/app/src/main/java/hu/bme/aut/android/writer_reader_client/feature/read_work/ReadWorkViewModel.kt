package hu.bme.aut.android.writer_reader_client.feature.read_work

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.get.Work
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import hu.bme.aut.android.writer_reader_client.feature.work_details.WorkDetailViewModel
import hu.bme.aut.android.writer_reader_client.feature.work_details.WorkDetailViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReadWorkState(
    val work: Work = Work(),
)


class ReadWorkViewModel(
    private val api : WriterReaderApi,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val workId: String = checkNotNull(savedStateHandle["workId"])

    private val _state = MutableStateFlow(WorkDetailViewState())
    val state = _state.asStateFlow()


    init {
        loadWork(workId)
    }

    fun loadWork(workId: String) {
        viewModelScope.launch(Dispatchers.IO){
            _state.update { it.copy(isLoading = true) }
            try {
                val loadedWork = api.getWork(workId)
                val work = loadedWork.body()?.data ?: Work()
                _state.update { it.copy(
                    work = work,
                    isLoading = false
                ) }

            } catch (e: Exception) {
                _state.update { it.copy(
                    isError = true,
                    throwable = e
                ) }
            }
        }

    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                WorkDetailViewModel(
                    savedStateHandle = savedStateHandle,
                    api = WriterReaderApplication.api
                )
            }
        }
    }
}