package galstyan.hayk.books.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LanguageEntity(
    @PrimaryKey
    val bookId: Long,
    val name: String,
)