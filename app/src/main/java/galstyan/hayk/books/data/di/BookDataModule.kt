package galstyan.hayk.books.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.books.data.BookLocalDataSource
import galstyan.hayk.books.data.BookRemoteDataSource
import galstyan.hayk.books.data.BookRepositoryImpl
import galstyan.hayk.books.domain.data.BookRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookDataModule {

    @Provides
    @Singleton
    fun provideRepository(
        bookLocalDataSource: BookLocalDataSource,
        bookRemoteDataSource: BookRemoteDataSource,
    ): BookRepository = BookRepositoryImpl(
        bookLocalDataSource,
        bookRemoteDataSource
    )
}