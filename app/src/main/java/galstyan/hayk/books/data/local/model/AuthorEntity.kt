package galstyan.hayk.books.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import galstyan.hayk.books.domain.model.Author

@Entity
data class AuthorEntity(
    @PrimaryKey
    val bookId: Long,
    val name: String,
    val birth: Int?,
    val death: Int?,
) {

    // Todo: Move mapping to a separate mapper (SoC/SRP, testability)
    fun toDomain() = Author(
        name = name,
        birthYear = birth,
        deathYear = death,
    )

    companion object {
        fun fromDomain(bookId: Long, author: Author) = AuthorEntity(
            bookId = bookId,
            name = author.name,
            birth = author.birthYear,
            death = author.deathYear,
        )
    }
}