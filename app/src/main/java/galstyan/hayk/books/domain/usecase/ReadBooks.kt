package galstyan.hayk.books.domain.usecase

import galstyan.hayk.books.domain.data.BookRepository
import javax.inject.Inject

// Use-cases help to have reusable, testable pieces of business logic
class ReadBooks @Inject constructor(
    private val bookRepository: BookRepository,
) {
    suspend operator fun invoke() = bookRepository.readBooks()
}