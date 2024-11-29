package galstyan.hayk.books.data.remote.gutendex.model

import kotlinx.serialization.Serializable

@Serializable
data class BooksPageDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookDto>
)