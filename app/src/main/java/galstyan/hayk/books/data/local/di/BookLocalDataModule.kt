package galstyan.hayk.books.data.local.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.books.data.BookLocalDataSource
import galstyan.hayk.books.data.local.BookDao
import galstyan.hayk.books.data.local.BookLocalDataSourceImpl
import galstyan.hayk.books.data.local.db.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookLocalDataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppDatabase.NAME
    ).build()

    @Provides
    @Singleton
    fun provideBookDao(db: AppDatabase): BookDao = db.bookDao()

    @Provides
    @Singleton
    fun provideDataSource(dao: BookDao): BookLocalDataSource = BookLocalDataSourceImpl(dao)
}