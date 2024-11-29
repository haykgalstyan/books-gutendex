package galstyan.hayk.books.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import galstyan.hayk.books.domain.model.Translator

@Entity
data class TranslatorEntity(
    @PrimaryKey
    val bookId: Long,
    val name: String,
    val birth: Int?,
    val death: Int?,
) {
    // Todo: Move to a separate mapper (SoC/SRP, testability)
    fun toDomain() = Translator(
        name = name,
        birthYear = birth,
        deathYear = death,
    )

    // Todo: Move to a separate mapper (SoC/SRP, testability)
    companion object {
        fun fromDomain(bookId: Long, translator: Translator) = TranslatorEntity(
            bookId = bookId,
            name = translator.name,
            birth = translator.birthYear,
            death = translator.deathYear,
        )
    }
}