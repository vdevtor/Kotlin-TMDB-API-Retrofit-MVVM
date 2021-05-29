package com.example.tmdbmvvm.data.api

import com.example.tmdbmvvm.data.api.ApiObject.API_AUTH_NAME
import com.example.tmdbmvvm.data.api.ApiObject.API_AUTH_VALUE
import com.example.tmdbmvvm.data.api.ApiObject.API_CONTENT_TYPE_NAME
import com.example.tmdbmvvm.data.api.ApiObject.API_CONTENT_TYPE_VALUE
import com.example.tmdbmvvm.data.api.ApiObject.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    val tmdbApi: TmdbService = getTmdbApiClient().create(TmdbService::class.java)

    private fun getTmdbApiClient(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
                .client(getInterceptorClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getInterceptorClient(): OkHttpClient {
        val logginIntercepetor = HttpLoggingInterceptor()
        logginIntercepetor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .callTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(logginIntercepetor)
                .addInterceptor { chain ->
                    val newRequest = chain.request()
                            .newBuilder()
                            .addHeader(API_AUTH_NAME, API_AUTH_VALUE)
                            .addHeader(API_CONTENT_TYPE_NAME, API_CONTENT_TYPE_VALUE)
                            .build()
                    chain.proceed(newRequest)
                }.addInterceptor { chain ->
                    val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .addQueryParameter("language", "pt-BR")
                            .build()

                    val newRequest = chain.request()
                            .newBuilder()
                            .url(url)
                            .build()
                    chain.proceed(newRequest)

                }
        return interceptor.build()
    }
}
