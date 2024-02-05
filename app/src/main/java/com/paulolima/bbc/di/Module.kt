package com.paulolima.bbc.di

import android.content.Context
import com.paulolima.bbc.BaseNewsApplication
import com.paulolima.bbc.HeadlineParams
import com.paulolima.bbc.data.api.NewsService
import com.paulolima.bbc.data.repository.MainRepository
import com.paulolima.bbc.data.repository.MainRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun providesApplication(
        @ApplicationContext
        appContext: Context
    ): BaseNewsApplication = appContext as BaseNewsApplication

    @Provides
    fun provideRetrofitClient(): Retrofit {
        // Add the interceptor to OkHttpClient
        // Add the interceptor to OkHttpClient

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apiKey", "94384625cf614311af4e3027cf0d384b").build()
            request.url(url)
            chain.proceed(request.build())
        })
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY))
        // Creating a client out of the builder

        // Creating a client out of the builder
        val client: OkHttpClient = builder.build()

        return Retrofit.Builder()
            .baseUrl(HeadlineParams.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRestNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    fun provideMainRepository(apiService: NewsService): MainRepositoryInterface {
        return MainRepository(apiService)
    }
}
