package com.hsnozan.basemvvm.di

import android.content.Context
import android.os.Environment
import com.hsnozan.basemvvm.BaseApplication
import com.hsnozan.basemvvm.api.BaseService
import com.hsnozan.basemvvm.ui.LoginViewModel
import com.hsnozan.basemvvm.utils.Constants
import okhttp3.Cache
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val baseApplication = BaseApplication()
private val context : Context = BaseApplication.context
private val dispatcher : Dispatcher = Dispatcher()
private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()
private val cache = Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024)
private val okHttpClient : OkHttpClient = provideOkHTTPClient()
private val retrofit : Retrofit = createRetrofitBuilder()
private val baseService : BaseService = retrofit.create(BaseService::class.java)


fun provideOkHTTPClient(): OkHttpClient {
    dispatcher.maxRequests = 4
    interceptor.level = HttpLoggingInterceptor.Level.BASIC

    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.HOURS)
        .writeTimeout(30, TimeUnit.HOURS)
        .readTimeout(30, TimeUnit.HOURS)
        .dispatcher(dispatcher)
        .cache(cache)
        .build()
}

fun createRetrofitBuilder(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val networkModule = module {
    single { baseService }
}

val viewModelModule = module {
    viewModel { LoginViewModel(baseApplication = baseApplication, baseService = baseService) }
}