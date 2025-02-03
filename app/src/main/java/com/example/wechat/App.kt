package com.example.wechat

import android.app.Application
import com.example.wechat.di.appModule
import com.example.wechat.features.auth.di.authModule
import com.example.wechat.features.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    authModule,
                    homeModule
                )
            )
        }
    }
}