package galstyan.hayk.books.data.remote.gutendex.api

import galstyan.hayk.books.data.remote.gutendex.model.BooksPageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GutendexClient {
    @GET("books")
    suspend fun getBooks(@Query("page") page: Int): Response<BooksPageDto>
}