package galstyan.hayk.books.presentation.books.list

import androidx.navigation.fragment.FragmentNavigator
import galstyan.hayk.books.domain.model.Book

sealed interface BookListAction {
    data object LoadMore : BookListAction
    data object Reload : BookListAction
    data class Open(val book: Book, val extras: FragmentNavigator.Extras) : BookListAction
    data object OpenDone : BookListAction
}
