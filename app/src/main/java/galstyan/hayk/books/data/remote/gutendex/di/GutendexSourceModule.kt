package galstyan.hayk.books.data.remote.gutendex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import galstyan.hayk.books.data.BookRemoteDataSource
import galstyan.hayk.books.data.remote.gutendex.GutendexBookDataSource
import galstyan.hayk.books.data.remote.gutendex.api.GutendexClientFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GutendexSourceModule {
    @Provides
    @Singleton
    fun provideClientFactory() = GutendexClientFactory()

    @Provides
    @Singleton
    fun provideDataSource(
        clientFactory: GutendexClientFactory,
    ): BookRemoteDataSource = GutendexBookDataSource(
        clientFactory.create()
    )
}