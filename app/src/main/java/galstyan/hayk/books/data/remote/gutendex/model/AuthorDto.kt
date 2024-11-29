package galstyan.hayk.books.data.remote.gutendex.model

import galstyan.hayk.books.domain.model.Author
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val name: String,
    val birth_year: Int?,
    val death_year: Int?,
) {
    fun toDomain() = Author(
        name = name,
        birthYear = birth_year,
        deathYear = death_year,
    )
}