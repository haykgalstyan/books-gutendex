package galstyan.hayk.books.domain.usecase

import galstyan.hayk.books.domain.data.BookRepository
import javax.inject.Inject

class ReadBook @Inject constructor(
    private val bookRepository: BookRepository,
) {
    suspend operator fun invoke(bookId: Long) = bookRepository.readBook(bookId)
}