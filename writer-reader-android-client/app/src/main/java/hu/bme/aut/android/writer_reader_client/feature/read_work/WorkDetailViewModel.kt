package hu.bme.aut.android.writer_reader_client.feature.read_work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.feature.home.HomeViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow


data class WorkDetailViewState(
    val comments: List<Comment> = listOf(
        Comment(
            author = "John Doe",
            timestamp = "2021. ápr. 01. 12:00",
            text = "This is a commentaasdfasd."
        ),
        Comment(
            author = "John Doe",
            timestamp = "2021. ápr. 01. 12:00",
            text = "This is a comment."
        )
    ),
    val newComment: String = "",
    val title: String = "The Great Gatsby",
    val author: String = "F. Scott Fitzgerald",
    val date: String = "2021. ápr. 01.",
    val category: String = "Novel",
    val tags: List<String> = listOf("fiction", "novel", "Harry Potter", "danger", "idk", "valami hosszú szöveg"),
    val isLiked: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val throwable: Throwable? = null
)


sealed class WorkDetailViewIntent {

}

class WorkDetailViewModel (): ViewModel() {
    private val _state = MutableStateFlow(WorkDetailViewState())
    val state = _state.asStateFlow()


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                WorkDetailViewModel()
            }
        }
    }
}