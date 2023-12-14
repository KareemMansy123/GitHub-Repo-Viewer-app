package com.example.strarterandroid

import android.app.Application
import com.example.strarterandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application(){
    override fun onCreate() {
        super.onCreate()
         startKoin {
             AndroidLogger(Level.NONE)
             androidContext(this@App)
             modules(appModule)
         }
    }
}