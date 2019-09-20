package com.hsnozan.basemvvm

import android.app.Application
import android.content.Context
import com.hsnozan.basemvvm.di.applicationModule
import com.hsnozan.basemvvm.di.databaseModule
import com.hsnozan.basemvvm.di.networkModule
import com.hsnozan.basemvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    companion object {
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(networkModule, viewModelModule, applicationModule, databaseModule))
        }
    }
}