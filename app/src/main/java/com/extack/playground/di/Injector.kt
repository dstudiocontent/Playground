package com.extack.playground.di


object Injector {
    private var component: SingletonComponent? = null

    fun initialize(component: SingletonComponent) {
        Injector.component = component
    }

    fun get(): SingletonComponent {
        return component ?: throw AssertionError("component should be set")
    }

}