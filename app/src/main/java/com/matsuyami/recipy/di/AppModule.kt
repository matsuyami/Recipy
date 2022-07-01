package com.matsuyami.recipy.di

import com.matsuyami.recipy.data.network.TastyService
import com.matsuyami.recipy.data.repositories.RecipeSearchRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideTastyService(client: OkHttpClient): TastyService {
        return Retrofit.Builder()
            .baseUrl("https://tasty.p.rapidapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TastyService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeSearchRepo(service: TastyService): RecipeSearchRepo {
        return RecipeSearchRepo(service)
    }
}
