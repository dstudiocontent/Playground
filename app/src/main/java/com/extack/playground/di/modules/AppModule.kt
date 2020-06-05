package com.extack.playground.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @JvmStatic
    @Provides
    fun appContext(app: Application): Context = app

}