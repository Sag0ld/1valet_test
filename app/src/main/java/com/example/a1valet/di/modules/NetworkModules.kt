package com.example.a1valet.di.modules

import com.example.a1valet.BuildConfig
import com.example.a1valet.datasources.CompanyAPI
import com.example.a1valet.di.MockInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModules = module {
    factory { MockInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideCompanyAPI(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(mockInterceptor).build()
}

fun provideCompanyAPI(retrofit: Retrofit): CompanyAPI =
    retrofit.create(CompanyAPI::class.java)
