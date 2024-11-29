package galstyan.hayk.books.domain.model

data class Book(
    val id: Long,
    val title: String,
    val authors: List<Author>,
    val translators: List<Translator>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val downloads: Int,
    val cover: String,
)