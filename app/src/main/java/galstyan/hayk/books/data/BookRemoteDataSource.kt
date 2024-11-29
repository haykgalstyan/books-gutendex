package galstyan.hayk.books.data

import galstyan.hayk.books.domain.model.Book

interface BookRemoteDataSource {
    suspend fun readBooks(page: Int): List<Book>
}