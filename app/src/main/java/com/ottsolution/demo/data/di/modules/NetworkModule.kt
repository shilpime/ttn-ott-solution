package com.ottsolution.demo.data.di.modules

import com.ottsolution.demo.data.networking.ApplicationApis
import com.ottsolution.demo.data.networking.services.ConfigService
import com.ottsolution.demo.data.networking.services.UserAccountService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {

        val interceptors = arrayListOf<Interceptor>()

        val loggingInterceptorBasic = HttpLoggingInterceptor()
        loggingInterceptorBasic.level = HttpLoggingInterceptor.Level.BASIC

        val loggingInterceptorBody = HttpLoggingInterceptor()
        loggingInterceptorBody.level = HttpLoggingInterceptor.Level.BODY

        interceptors.add(loggingInterceptorBasic)
        interceptors.add(loggingInterceptorBody)

        return interceptors
    }

    @Singleton
    @Provides
    fun provideRetrofit(interceptors: ArrayList<Interceptor>): Retrofit {
        val clientBuilder =
            OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
        if (interceptors.isNotEmpty()) {
            interceptors.forEach { interceptor ->
                clientBuilder.addInterceptor(interceptor)
            }
        }

        return Retrofit.Builder()
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    /**
     * Provide Retrofit API's service from here
     */
    @Singleton
    @Provides
    fun provideUserAccountService(retrofit: Retrofit): UserAccountService {
        return UserAccountService(retrofit.create(ApplicationApis::class.java))
    }

    @Singleton
    @Provides
    fun provideConfigService(retrofit: Retrofit): ConfigService {
        return ConfigService(retrofit.create(ApplicationApis::class.java))
    }
}