package galstyan.hayk.books.data

import galstyan.hayk.books.domain.data.BookRepository
import galstyan.hayk.books.domain.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(
    private val bookLocalDataSource: BookLocalDataSource,
    private val bookRemoteDataSource: BookRemoteDataSource,
) : BookRepository() {

    // Simplest solution due to time constraints
    private var page = 1

    override suspend fun readBooks(): Flow<List<Book>> {
        val remote = bookRemoteDataSource.readBooks(page++)
        bookLocalDataSource.writeBooks(remote)
        return bookLocalDataSource.readBooks()
    }

    override suspend fun readBook(id: Long): Flow<Book> {
        return bookLocalDataSource.readBook(id)
    }
}