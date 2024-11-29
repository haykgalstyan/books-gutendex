package galstyan.hayk.books.data.remote.gutendex.model

import galstyan.hayk.books.domain.model.Book
import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val id: Long,
    val title: String,
    val authors: List<AuthorDto>,
    val translators: List<TranslatorDto>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean,
    val media_type: String,
    val formats: Map<String, String>,
    val download_count: Int
) {
    val image = formats.filterKeys {
        // Not sure if there are other image types
        it.startsWith("image")
    }.values.firstOrNull()

    // Todo: See if can get away with using map for formats, otherwise use this
    //    data class Formats(
    //        @SerializedName("image/jpeg") val image: String,
    //    )


    fun toDomain() = Book(
        id = id,
        title = title,
        authors = authors.map { it.toDomain() },
        translators = translators.map { it.toDomain() },
        subjects = subjects,
        bookshelves = bookshelves,
        languages = languages,
        downloads = download_count,
        cover = image ?: "" // todo: consider making nullable
    )
}