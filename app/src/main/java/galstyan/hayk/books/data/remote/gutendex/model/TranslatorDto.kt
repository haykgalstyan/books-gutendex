package galstyan.hayk.books.data.remote.gutendex.model

import galstyan.hayk.books.domain.model.Translator
import kotlinx.serialization.Serializable

@Serializable
data class TranslatorDto(
    val name: String,
    val birth_year: Int?,
    val death_year: Int?,
) {
    fun toDomain() = Translator(
        name = name,
        birthYear = birth_year,
        deathYear = death_year,
    )
}