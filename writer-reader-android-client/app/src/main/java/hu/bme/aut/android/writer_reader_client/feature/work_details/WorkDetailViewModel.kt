package hu.bme.aut.android.writer_reader_client.feature.work_details

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.get.Work
import hu.bme.aut.android.writer_reader_client.data.model.post.CommentRequest
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeRequest
import hu.bme.aut.android.writer_reader_client.data.preferences.DataStoreManager
import hu.bme.aut.android.writer_reader_client.data.repository.ApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WorkDetailViewState(
    val newComment: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val throwable: Throwable? = null,
    val work: Work = Work(),
    val userToken: String = "",
    val userEmail: String = ""
)

sealed class WorkDetailViewIntent {
    object LikeWorkButtonClicked : WorkDetailViewIntent()
    data class CommentChanged(val text: String) : WorkDetailViewIntent()
    object CommentSendButtonClicked : WorkDetailViewIntent()
    data class LikeCommentButtonClicked(val commentId: String) : WorkDetailViewIntent()
    object ErrorOkButtonClicked : WorkDetailViewIntent()

}

@OptIn(FlowPreview::class)
class WorkDetailViewModel (
    private val savedStateHandle: SavedStateHandle,
    private val apiManager: ApiManager,
    private val context: Context,
    private val onNavigateToLogin: () -> Unit
): ViewModel() {

    private val workId: String = checkNotNull(savedStateHandle["workId"])

    private val _state = MutableStateFlow(WorkDetailViewState())
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch {
            DataStoreManager.getUserTokenFlow(context).debounce(300).collect { token ->
                _state.update { it.copy(userToken = token ?: "") }
                if (_state.value.userToken.isEmpty()) {
                    Log.e("DataStore", "Token not found")
                } else {
                    Log.d("DataStore", "Token successfully loaded: ${_state.value.userToken}")
                }
            }
        }
        loadWork(workId)
    }


    private fun loadWork(workId: String)
    {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO){
            apiManager.getWork(
                id = workId,
                onSuccess = { workResponse ->
                    _state.update {
                        it.copy(
                            work = workResponse.data,
                            isLoading = false
                        )
                    }
                    println("Work response: ${workResponse.data}")
                },
                onError = { errorMessage ->
                    _state.update {
                        it.copy(
                            isError = true,
                            isLoading = false,
                            throwable = Exception(errorMessage)
                        )
                    }
                    println("Error: $errorMessage")
                }
            )
        }
    }

    fun onIntent(intent: WorkDetailViewIntent){
        if(_state.value.userToken.isEmpty()){
            onNavigateToLogin()
        }else
        {
            viewModelScope.launch {
                when (intent) {
                    is WorkDetailViewIntent.ErrorOkButtonClicked -> {
                        _state.update {
                            it.copy(
                                isError = false,
                                throwable = null
                            )
                        }
                        loadWork(workId)
                    }
                    is WorkDetailViewIntent.LikeWorkButtonClicked -> {
                        launch {
                            apiManager.postLike(
                                token = _state.value.userToken,
                                likeRequest = LikeRequest(
                                    likeableId = workId,
                                    likeableType = "App\\Models\\Work"
                                ),
                                onSuccess = { likeResponse ->
                                    loadWork(_state.value.work.id)
                                    println("Like response: ${likeResponse.data}")
                                },
                                onError = { errorMessage ->
                                    _state.update {
                                        it.copy(
                                            isError = true,
                                            isLoading = false,
                                            throwable = Exception(errorMessage)
                                        )
                                    }
                                    println("Error: $errorMessage")
                                }
                            )
                            _state.update {
                                it.copy(
                                    work = it.work.copy(
                                        isLiked = !it.work.isLiked,
                                        likes = it.work.likes + if (it.work.isLiked) -1 else 1
                                    )
                                )
                            }
                        }
                    }
                    is WorkDetailViewIntent.CommentChanged -> {
                        _state.update { it.copy(newComment = intent.text) }
                    }
                    is WorkDetailViewIntent.CommentSendButtonClicked -> {
                        val newComment = _state.value.newComment
                        launch {
                            println(_state.value.userToken)
                            println(newComment)
                            _state.update { it.copy(isLoading = true) }

                            apiManager.postComment(
                                token = _state.value.userToken,
                                commentRequest = CommentRequest(
                                    content = newComment,
                                    commentableId = _state.value.work.id,
                                    commentableType = "App\\Models\\Work"
                                ),
                                onSuccess = { commentResponse ->
                                    loadWork(_state.value.work.id)
                                    println("Comment response: ${commentResponse.data}")
                                },
                                onError = { errorMessage ->
                                    _state.update {
                                        it.copy(
                                            isError = true,
                                            isLoading = false,
                                            throwable = Exception(errorMessage)
                                        )
                                    }
                                    println("Error: $errorMessage")
                                }
                            )
                        }
                        _state.update { it.copy(newComment = "") }
                    }
                    is WorkDetailViewIntent.LikeCommentButtonClicked -> {
                        launch {
                            apiManager.postLike(
                                token = _state.value.userToken,
                                likeRequest = LikeRequest(
                                    likeableId = intent.commentId,
                                    likeableType = "App\\Models\\Comment"
                                ),
                                onSuccess = { likeResponse ->
                                    loadWork(_state.value.work.id)
                                    println("Like response: ${likeResponse.data}")
                                },
                                onError = { errorMessage ->
                                    _state.update {
                                        it.copy(
                                            isError = true,
                                            isLoading = false,
                                            throwable = Exception(errorMessage)
                                        )
                                    }
                                    println("Error: $errorMessage")
                                }
                            )
                        }
                        println("Like Comment with Comment id: $intent.commentId")
                        val updatedComments = _state.value.work.comments.map { comment ->
                            if (comment.id == intent.commentId) {
                                comment.copy(
                                    likes = comment.likes + if (comment.isLiked) -1 else 1,
                                    isLiked = !comment.isLiked
                                )
                            } else {
                                comment
                            }
                        }
                        _state.update { it.copy(work = it.work.copy(comments = updatedComments)) }
                    }
                }
            }
        }
    }



    companion object {
        fun Factory(context: Context, onNavigateToLogin: () -> Unit): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                WorkDetailViewModel(
                    savedStateHandle = savedStateHandle,
                    apiManager = WriterReaderApplication.apiManager,
                    context = context,
                    onNavigateToLogin = onNavigateToLogin
                )
            }
        }
    }
}