package galstyan.hayk.books.data.local.model

import androidx.room.Embedded
import androidx.room.Relation
import galstyan.hayk.books.domain.model.Book


// TODO: In reality the relationships like authors are many-to-many,
//  but that would require more time which unfortunately I don't have right now :/
data class BookWithRelations(
    @Embedded val book: BookEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val authors: List<AuthorEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val translators: List<TranslatorEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val languages: List<LanguageEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val bookshelves: List<BookshelfEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val subjects: List<SubjectEntity>,
) {
    fun toDomain() = Book(
        id = book.id,
        title = book.title,
        authors = authors.map { it.toDomain() },
        translators = translators.map { it.toDomain() },
        languages = languages.map { it.name },
        bookshelves = bookshelves.map { it.name },
        subjects = subjects.map { it.name },
        downloads = book.downloads,
        cover = book.cover
    )

    companion object {
        fun fromDomain(book: Book) = BookWithRelations(
            book = BookEntity(
                id = book.id,
                title = book.title,
                downloads = book.downloads,
                cover = book.cover
            ),
            authors = book.authors.map { AuthorEntity.fromDomain(book.id, it) },
            translators = book.translators.map { TranslatorEntity.fromDomain(book.id, it) },
            languages = book.languages.map { LanguageEntity(book.id, it) },
            bookshelves = book.bookshelves.map { BookshelfEntity(book.id, it) },
            subjects = book.subjects.map { SubjectEntity(book.id, it) },
        )
    }

}