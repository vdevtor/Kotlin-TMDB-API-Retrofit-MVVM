package com.example.tmdbmvvm.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tmdbmvvm.data.api.TmdbService
import com.example.tmdbmvvm.utils.Constants.Api.API_AUTH_NAME
import com.example.tmdbmvvm.utils.Constants.Api.API_AUTH_VALUE
import com.example.tmdbmvvm.utils.Constants.Api.API_CONTENT_TYPE_NAME
import com.example.tmdbmvvm.utils.Constants.Api.API_CONTENT_TYPE_VALUE
import com.example.tmdbmvvm.utils.Constants.Api.BASE_URL
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import junitparams.JUnitParamsRunner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@RunWith(JUnitParamsRunner::class)
class ApiServiceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var intercepetor: OkHttpClient
    lateinit var service: TmdbService
    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        getInterceptorClient()
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(intercepetor)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(TmdbService::class.java)
    }

    @Before
    fun getInterceptorClient() {
        val logginIntercepetor = HttpLoggingInterceptor()
        logginIntercepetor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .callTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(logginIntercepetor)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                            .addHeader(API_AUTH_NAME, API_AUTH_VALUE)
                            .addHeader(API_CONTENT_TYPE_NAME, API_CONTENT_TYPE_VALUE)
                            .build()
                    chain.proceed(newRequest)
                }
        intercepetor = interceptor.build()
    }

    @After
    @Throws(IOException::class)
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `testando o filme em destaque`() {
        val responseCoroutine = GlobalScope
        runBlocking {
            responseCoroutine.launch {
                val response = service.getMovie(464052)
                assertEquals(464052, response.body()?.id)
                assertNotNull(response)
            }
        }
    }

    @Test
    fun `testando a lista de filmes similares`() {
        val responseCoroutine2 = GlobalScope
        runBlocking {
            responseCoroutine2.launch {
                val response = service.getSimilarMovies(464052)
                response.body()?.resultSimilarMovies?.get(5).let {
                    assertEquals(13640, it?.id)
                }
            }
        }
    }

    @Test
    fun `testando o path padrao`() {
        val request = mockWebServer.takeRequest(2000, TimeUnit.MILLISECONDS)
        assertEquals(request?.path, "https://api.themoviedb.org/3/")
    }


}