package com.fleming.tiket.di.module

import com.fleming.tiket.BuildConfig
import com.fleming.tiket.base.RxErrorHandlingCallAdapterFactory
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [])
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        gson: Gson,
                        schedulers: BaseSchedulerProvider): Retrofit {
        val baseUrl = "https://api.github.com/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create(schedulers))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val httpInterceptor = HttpLoggingInterceptor()
            httpInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpInterceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor.invoke { chain ->
            var request = chain.request()
            val buildRequest = request.newBuilder().apply {
                addHeader("Content-Type", "application/json")
                addHeader("Accept", "application/json")
            }
            request = buildRequest.build()
            chain.proceed(request)
        }

}
