package hu.bme.aut.android.writer_reader_client.feature.work_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.get.Chapter
import hu.bme.aut.android.writer_reader_client.data.model.get.Work
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import hu.bme.aut.android.writer_reader_client.ui.common.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
data class// Work2(
    val id: String = "",
    val title: String = "ASDASD",
    val creatorName: String = "xyz",
    val creatorId: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",

    val language: String = "",
    val category: String = "",
    val rating: String = "",
    val characters: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val likes: Int = 0,

    val chapters: List<Chapter> = listOf(
        Chapter(
        "asd",
        "asd",
        "asd",
    )
    ),
    val content: String = "asdkhjfb askldjv ajklsdhfl kajsnd vdbflkja sdkljvfan dsflakdna fsndékjf adfknja nldskfjéals dnmfélas dvékjadéf lkasdnév nékadn féjasd vékjdnfém dfékjnéadsf a. ksdn éad flansél dfnaé fdkjadf hnfkasd fn asdkfasl kdfja",
    val comments: List<Comment> = emptyList(),
)
*/
data class WorkDetailViewState(
    val comments: List<Comment> = listOf(Comment(),Comment()),
    val newComment: String = "",
    val title: String = "The Great Gatsby",
    val author: String = "F. Scott Fitzgerald",
    val date: String = "2021. ápr. 01.",
    val category: String = "Novel",
    val tags: List<String> = listOf("fiction", "novel", "Harry Potter", "danger", "idk", "valami hosszú szöveg"),
    val isLiked: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val throwable: Throwable? = null,

    val work: Work = Work(),
)


sealed class WorkDetailViewIntent {
    object LikeWorkButtonClicked : WorkDetailViewIntent()
    data class CommentChanged(val text: String) : WorkDetailViewIntent()
    object CommentSendButtonClicked : WorkDetailViewIntent()
    data class LikeCommentButtonClicked(val commentId: String) : WorkDetailViewIntent()


}

class WorkDetailViewModel (
    private val savedStateHandle: SavedStateHandle,
    private val api: WriterReaderApi
): ViewModel() {

    private val workId: String = checkNotNull(savedStateHandle["workId"])

    private val _state = MutableStateFlow(WorkDetailViewState())
    val state = _state.asStateFlow()
    init {
        loadWork(workId)
    }


    fun loadWork(
        workId: String
    ) {
        viewModelScope.launch(Dispatchers.IO){
            _state.update { it.copy(isLoading = true) }
            try {
                val loadedWork = api.getWork(workId)
                val work = loadedWork.body()?.data ?:Work()
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

    fun onIntent(intent: WorkDetailViewIntent){
        viewModelScope.launch {
            when (intent) {
                is WorkDetailViewIntent.LikeWorkButtonClicked -> {
                    //TODO: like work
                }
                is WorkDetailViewIntent.CommentChanged -> {
                    _state.update { it.copy(newComment = intent.text) }
                }
                is WorkDetailViewIntent.CommentSendButtonClicked -> {
                    //TODO: send comment
                    _state.update { it.copy(newComment = "") }
                }
                is WorkDetailViewIntent.LikeCommentButtonClicked -> {
                    //keComment(intent.commentId)
                }

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