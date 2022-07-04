package com.example.countries.di

import com.example.countries.data.remote.RapidApi
import com.example.countries.data.repository.RemoteDataRepositoryImpl
import com.example.countries.domain.repository.RemoteDataRepository
import com.example.countries.util.Constants.BASE_URL
import com.example.countries.util.dispatcher_provider.DispatcherProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }



    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRapidApi(retrofit: Retrofit): RapidApi{
        return retrofit.create(RapidApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataRepository(
        rapidApi: RapidApi,
        dispatcherProvider: DispatcherProvider
    ):RemoteDataRepository{
        return RemoteDataRepositoryImpl(
            rapidApi = rapidApi,
            dispatcherProvider = dispatcherProvider
        )
    }


}






























