package com.extack.playground.di

import android.app.Application
import com.extack.playground.di.modules.AppModule
import com.extack.playground.di.modules.AssistedInjectionModule
import com.extack.playground.di.modules.FirebaseModule
import com.extack.playground.di.modules.RetrofitModule
import com.extack.playground.ui.auth.signin.SignInViewModel
import com.extack.playground.ui.auth.signup.SignUpViewModel
import com.extack.playground.ui.dashboard.DashboardViewModel
import com.extack.playground.ui.home.HomeViewModel
import com.extack.playground.ui.main.ActivityVM
import com.extack.playground.ui.more.NotificationsViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AssistedInjectionModule::class, RetrofitModule::class, FirebaseModule::class])
interface SingletonComponent {

    fun mainActivityVMFactory(): ActivityVM.Factory

    fun signInVMFactory(): SignInViewModel.Factory
    fun signUpVMFactory(): SignUpViewModel.Factory

    fun homeVMFactory(): Provider<HomeViewModel>
    fun dashboardyVMFactory(): Provider<DashboardViewModel>
    fun notificationVMFactory(): Provider<NotificationsViewModel>


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): SingletonComponent
    }
}