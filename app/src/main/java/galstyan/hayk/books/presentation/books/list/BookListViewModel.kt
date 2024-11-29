package galstyan.hayk.books.presentation.books.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.books.domain.usecase.ReadBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val readBooks: ReadBooks,
) : ViewModel() {

    private val _uiState: MutableStateFlow<BookListUiState> = MutableStateFlow(
        BookListUiState.Loading
    )
    val uiState: StateFlow<BookListUiState> = _uiState

    init {
        readBooks()
    }

    fun action(action: BookListAction) {
        _uiState.update { reduce(_uiState.value, action) }
    }

    fun readBooks() {
        viewModelScope.launch {
            readBooks.invoke()
                .catch { _uiState.value = BookListUiState.ErrorUnknown }
                .collect { _uiState.value = BookListUiState.BookList(it) }
        }
    }

    // keep the function pure so its easy to move out in the future
    private fun reduce(
        state: BookListUiState,
        action: BookListAction,
    ): BookListUiState {
        return when (action) {
            is BookListAction.LoadMore -> {
                if (state is BookListUiState.Loading) return state
                readBooks()
                BookListUiState.Loading
            }

            is BookListAction.Open -> {
                state as BookListUiState.BookList
                BookListUiState.NavigateToDetails(action.book, state.books, action.extras)
            }

            is BookListAction.Reload -> {
                readBooks()
                BookListUiState.Loading
            }

            BookListAction.OpenDone -> {
                state as BookListUiState.NavigateToDetails
                BookListUiState.BookList(state.books.toList())
            }
        }
    }
}