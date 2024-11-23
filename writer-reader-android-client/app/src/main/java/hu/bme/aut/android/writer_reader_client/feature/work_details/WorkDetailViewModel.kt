package hu.bme.aut.android.writer_reader_client.feature.work_details

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.get.Work
import hu.bme.aut.android.writer_reader_client.data.model.post.LikeRequest
import hu.bme.aut.android.writer_reader_client.data.preferences.DataStoreManager
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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



}

class WorkDetailViewModel (
    private val savedStateHandle: SavedStateHandle,
    private val api: WriterReaderApi,
    private val context: Context,
): ViewModel() {

    private val workId: String = checkNotNull(savedStateHandle["workId"])

    private val _state = MutableStateFlow(WorkDetailViewState())
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch {
            DataStoreManager.getUserTokenFlow(context).collect {
                _state.update { it.copy(userToken = it.userToken, userEmail = it.userEmail) }}
        }
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
                    launch {
                        try {
                            api.postLike(
                                body = LikeRequest(
                                    likeableId = workId,
                                    likeableType = "App\\Models\\Work"
                                ),
                                authHeader =  _state.value.userToken
                            )
                        }
                        catch (e: Exception) {
                            println("Error: ${e.message}")
                        }
                    }

                    _state.update { it.copy(work = it.work.copy(
                        isLiked = !it.work.isLiked,
                        likes = it.work.likes + if (it.work.isLiked) -1 else 1
                    )) }

                }
                is WorkDetailViewIntent.CommentChanged -> {
                    _state.update { it.copy(newComment = intent.text) }
                }
                is WorkDetailViewIntent.CommentSendButtonClicked -> {
                    launch {
                        println("Comment: ${_state.value.newComment}")
                        try {
                           /* val commentResponse = api.createComment(
                                token = _state.value.userToken,
                                comment = Comment(
                                    content = _state.value.newComment,
                                    commentableId = workId,
                                    commentableType = "App\\Models\\Work"
                                )
                            )
                            println(commentResponse.body())
                            loadWork(workId)
*/
                        }catch (e: Exception) {
                            println("Error: ${e.message}")
                        }
                    }


                    _state.update { it.copy(newComment = "") }
                }
                is WorkDetailViewIntent.LikeCommentButtonClicked -> {
                    launch {
                        try {
                            api.postLike(
                                body = LikeRequest(
                                    likeableId = intent.commentId,
                                    likeableType = "App\\Models\\Comment"
                                ),
                                authHeader = _state.value.userToken
                            )
                        }
                        catch (e: Exception) {
                            println("Error: ${e.message}")
                        }
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



    companion object {
        fun Factory(context: Context): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                WorkDetailViewModel(
                    savedStateHandle = savedStateHandle,
                    api = WriterReaderApplication.api,
                    context = context
                )
            }
        }
    }
}