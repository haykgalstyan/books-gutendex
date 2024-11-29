package galstyan.hayk.books.domain.data

import galstyan.hayk.books.domain.model.Book
import kotlinx.coroutines.flow.Flow

abstract class BookRepository {
    abstract suspend fun readBooks(): Flow<List<Book>>
    abstract suspend fun readBook(id: Long): Flow<Book>
}