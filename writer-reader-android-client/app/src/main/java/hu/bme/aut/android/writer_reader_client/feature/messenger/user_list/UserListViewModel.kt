package hu.bme.aut.android.writer_reader_client.feature.messenger.user_list
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.get.User
import hu.bme.aut.android.writer_reader_client.data.remote.api.WriterReaderApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class UserListViewModel(
    private val api: WriterReaderApi
): ViewModel() {

    private val _state = MutableStateFlow(UserListState())
    val state = _state.asStateFlow()



    init {
        refreshUsers()
    }

    private fun refreshUsers() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loadedWorks = api.getUsers()
                val users = loadedWorks.body()?.data ?: emptyList()
                _state.update {
                    it.copy(
                        users = users,
                        isLoading = false,
                        isError = false,
                    )
                }

                Log.d("UseListViewModel", "Loaded users: $users")
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        throwable = e
                    )
                }
                Log.d("UserListViewModel", "Error loading users", e)
            }
        }
    }

    fun onSearchTextChange(value: String) {
        _state.update { it.copy(searchText = value) }
        searchWorks(state.value.searchText)
    }

    fun searchWorks(query: String) {
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




    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserListViewModel(
                    api = WriterReaderApplication.api
                )
            }
        }
    }
}

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val searchText: String = "",
    val isError: Boolean = false,
    val throwable: Throwable? = null
)


