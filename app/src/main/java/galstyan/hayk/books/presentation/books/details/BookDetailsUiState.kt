package galstyan.hayk.books.presentation.books.details

import galstyan.hayk.books.domain.model.Book

sealed interface BookDetailsUiState {
    data object Loading : BookDetailsUiState
    data class BookDetails(val book: Book) : BookDetailsUiState
    data object ErrorUnknown : BookDetailsUiState
}