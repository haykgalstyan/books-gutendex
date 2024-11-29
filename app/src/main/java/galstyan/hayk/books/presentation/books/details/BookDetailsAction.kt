package galstyan.hayk.books.presentation.books.details

sealed interface BookDetailsAction {
    data object Reload : BookDetailsAction
}
