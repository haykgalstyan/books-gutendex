package galstyan.hayk.books.presentation.books.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import galstyan.hayk.books.domain.usecase.ReadBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val readBook: ReadBook,
) : ViewModel() {

    private val args = BookDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _uiState: MutableStateFlow<BookDetailsUiState> = MutableStateFlow(
        BookDetailsUiState.Loading
    )
    val uiState: StateFlow<BookDetailsUiState> = _uiState

    init {
        readBook()
    }

    fun action(action: BookDetailsAction) {
        _uiState.update { reduce(_uiState.value, action) }
    }

    private fun readBook() {
        viewModelScope.launch {
            readBook.invoke(args.bookId)
                .catch { _uiState.value = BookDetailsUiState.ErrorUnknown }
                .collect { _uiState.value = BookDetailsUiState.BookDetails(it) }
        }
    }

    private fun reduce(
        state: BookDetailsUiState,
        action: BookDetailsAction,
    ): BookDetailsUiState {
        return when (action) {
            is BookDetailsAction.Reload -> {
                readBook()
                BookDetailsUiState.Loading
            }
        }
    }
}