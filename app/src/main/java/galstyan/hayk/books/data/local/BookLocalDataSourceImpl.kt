package galstyan.hayk.books.data.local

import galstyan.hayk.books.data.BookLocalDataSource
import galstyan.hayk.books.data.local.model.BookWithRelations
import galstyan.hayk.books.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookLocalDataSourceImpl(
    private val bookDao: BookDao
) : BookLocalDataSource {
    override suspend fun readBooks(): Flow<List<Book>> {
        return bookDao.readBooks().map { books -> books.map { it.toDomain() } }
    }

    override suspend fun readBook(id: Long): Flow<Book> {
        return bookDao.readBook(id).map { it.toDomain() }
    }

    override suspend fun writeBooks(books: List<Book>) {
        bookDao.writeBooksWithRelations(books.map { BookWithRelations.fromDomain(it) })
    }
}