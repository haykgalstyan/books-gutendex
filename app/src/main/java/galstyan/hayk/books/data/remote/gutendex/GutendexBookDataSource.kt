package galstyan.hayk.books.data.remote.gutendex

import galstyan.hayk.books.data.BookRemoteDataSource
import galstyan.hayk.books.data.remote.gutendex.api.GutendexClient
import galstyan.hayk.books.domain.model.Book

class GutendexBookDataSource(
    private val client: GutendexClient,
) : BookRemoteDataSource {
    override suspend fun readBooks(page: Int): List<Book> {
        require(page > 0) { "Page start with 1" }
        try {
            val response = client.getBooks(page)
            if (response.isSuccessful) {
                val bookDtoList = response.body()?.results ?: emptyList()
                return bookDtoList.map { it.toDomain() }
            }
        } catch (e: Exception) {
            // todo: handle errors
            e.printStackTrace()
        }

        return emptyList()
    }
}