package galstyan.hayk.books.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import galstyan.hayk.books.domain.model.Book

const val bookTable = "books"

@Entity(tableName = bookTable)
data class BookEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val downloads: Int,
    val cover: String,
) {

    // Todo: Move to a separate mapper (SoC/SRP, testability)
    companion object {
        fun fromDomain(book: Book) = BookEntity(
            id = book.id,
            title = book.title,
            downloads = book.downloads,
            cover = book.cover
        )
    }
}