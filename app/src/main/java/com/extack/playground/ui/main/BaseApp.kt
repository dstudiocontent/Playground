package com.extack.playground.ui.main

import android.app.Application
import com.extack.playground.di.DaggerSingletonComponent
import com.extack.playground.di.Injector

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.initialize(DaggerSingletonComponent.factory().create(this))
    }

    /*companion object {
        lateinit var instance: BaseApp
            private set
    }*/
}