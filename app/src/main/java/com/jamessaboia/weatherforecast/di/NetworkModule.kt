package com.jamessaboia.weatherforecast.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jamessaboia.weatherforecast.data.network.API_KEY
import com.jamessaboia.weatherforecast.data.network.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


val networkModule = module {

    val requestInterceptor = Interceptor { chain ->
        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("key", API_KEY)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }

    val builder = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .build()

    single<Retrofit> {
        Retrofit.Builder()
            .client(builder)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create<Api>()
    }
}