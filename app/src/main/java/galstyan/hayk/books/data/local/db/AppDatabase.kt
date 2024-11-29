package galstyan.hayk.books.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import galstyan.hayk.books.data.local.BookDao
import galstyan.hayk.books.data.local.model.AuthorEntity
import galstyan.hayk.books.data.local.model.BookEntity
import galstyan.hayk.books.data.local.model.BookshelfEntity
import galstyan.hayk.books.data.local.model.LanguageEntity
import galstyan.hayk.books.data.local.model.SubjectEntity
import galstyan.hayk.books.data.local.model.TranslatorEntity

@Database(
    entities = [
        BookEntity::class,
        AuthorEntity::class,
        LanguageEntity::class,
        TranslatorEntity::class,
        BookshelfEntity::class,
        SubjectEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "app_database"
    }

    abstract fun bookDao(): BookDao
}