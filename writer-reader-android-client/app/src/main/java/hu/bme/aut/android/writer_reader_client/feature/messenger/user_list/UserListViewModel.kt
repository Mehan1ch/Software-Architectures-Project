package hu.bme.aut.android.writer_reader_client.feature.messenger.user_list
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import hu.bme.aut.android.writer_reader_client.WriterReaderApplication
import hu.bme.aut.android.writer_reader_client.data.model.get.UsersData
import hu.bme.aut.android.writer_reader_client.data.preferences.DataStoreManager
import hu.bme.aut.android.writer_reader_client.data.repository.ApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch




@OptIn(FlowPreview::class)
class UserListViewModel(
    private val apiManager: ApiManager,
    private val context: Context,
    private val onTokenNotFound: () -> Unit

    ): ViewModel() {

    private val _state = MutableStateFlow(UserListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            DataStoreManager.getUserTokenFlow(context).debounce(300).collect { token ->
                _state.update { it.copy(token = token ?: "") }
                if (_state.value.token.isEmpty()) {
                    Log.e("DataStore", "Token not found")
                    onTokenNotFound()


                } else {
                    Log.d("DataStore", "Token successfully loaded: ${_state.value.token}")
                }
            }
        }
        viewModelScope.launch {
            refreshUsers()
        }
    }

    private  fun refreshUsers() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            apiManager.getUsers(
                onSuccess = { usersResponse ->
                    _state.update {
                        it.copy(
                            users = usersResponse.data,
                            isLoading = false,
                        )
                    }
                    println("Users: ${usersResponse.data}")
                },
                onError = { errorMessage ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            throwable = Exception(errorMessage)
                        )
                    }
                    println("Error: $errorMessage")
                }
            )
        }
    }

    fun onSearchTextChange(value: String) {
        _state.update { it.copy(searchText = value) }
        searchUsers(state.value.searchText)
    }

    private fun searchUsers(query: String) {
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
        fun Factory(context: Context, onTokenNotFound: () -> Unit): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserListViewModel(
                    apiManager = WriterReaderApplication.apiManager,
                    context = context,
                    onTokenNotFound = onTokenNotFound
                )
            }
        }
    }
}

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<UsersData> = emptyList(),
    val searchText: String = "",
    val isError: Boolean = false,
    val throwable: Throwable? = null,
    val token: String = ""
)


