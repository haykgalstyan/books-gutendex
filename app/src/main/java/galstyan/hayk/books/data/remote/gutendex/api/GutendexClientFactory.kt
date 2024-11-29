package galstyan.hayk.books.data.remote.gutendex.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


class GutendexClientFactory {

    fun create(): GutendexClient = Retrofit.Builder()
        .baseUrl("https://gutendex.com/")
        .addConverterFactory(
            Json.asConverterFactory(
                "application/json; charset=UTF8".toMediaType()
            )
        ).build()
        .create(GutendexClient::class.java)
}