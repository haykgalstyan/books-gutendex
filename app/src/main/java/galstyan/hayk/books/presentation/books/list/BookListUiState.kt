package galstyan.hayk.books.presentation.books.list

import androidx.navigation.Navigator
import galstyan.hayk.books.domain.model.Book

sealed interface BookListUiState {
    data object Loading : BookListUiState
    data class BookList(val books: List<Book>) : BookListUiState
    data object ErrorUnknown : BookListUiState
    data class NavigateToDetails(
        val book: Book,
        val books: List<Book>,
        val navigationExtras: Navigator.Extras
    ) : BookListUiState
}