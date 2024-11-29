package galstyan.hayk.books.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import galstyan.hayk.books.data.local.model.AuthorEntity
import galstyan.hayk.books.data.local.model.BookEntity
import galstyan.hayk.books.data.local.model.BookWithRelations
import galstyan.hayk.books.data.local.model.BookshelfEntity
import galstyan.hayk.books.data.local.model.LanguageEntity
import galstyan.hayk.books.data.local.model.SubjectEntity
import galstyan.hayk.books.data.local.model.TranslatorEntity
import galstyan.hayk.books.data.local.model.bookTable
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Transaction
    @Query("SELECT * FROM $bookTable")
    fun readBooks(): Flow<List<BookWithRelations>>

    @Transaction
    @Query("SELECT * FROM $bookTable WHERE id = :bookId")
    fun readBook(bookId: Long): Flow<BookWithRelations>

    // TODO: Optimize
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun writeBooksWithRelations(
        books: List<BookWithRelations>
    ): List<Long> {
        writeAuthors(books.flatMap { it.authors })
        writeTranslators(books.flatMap { it.translators })
        writeLanguages(books.flatMap { it.languages })
        writeBookshelves(books.flatMap { it.bookshelves })
        writeSubjects(books.flatMap { it.subjects })
        return writeBooks(books.map { it.book })
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun writeBooks(books: List<BookEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun writeAuthors(authors: List<AuthorEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun writeTranslators(translators: List<TranslatorEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun writeLanguages(languages: List<LanguageEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun writeBookshelves(genres: List<BookshelfEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun writeSubjects(bookshelfBooks: List<SubjectEntity>): List<Long>
}