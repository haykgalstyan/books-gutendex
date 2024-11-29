package galstyan.hayk.books.data

import galstyan.hayk.books.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookLocalDataSource {
    suspend fun readBooks(): Flow<List<Book>>
    suspend fun readBook(id: Long): Flow<Book>
    suspend fun writeBooks(books: List<Book>)
}