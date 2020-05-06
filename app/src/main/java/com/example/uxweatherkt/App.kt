package com.example.uxweatherkt

import android.app.Application

class App : Application() {

    private lateinit var dependencyRoot: DependencyRoot

    override fun onCreate() {
        super.onCreate()
        dependencyRoot = DependencyRoot()
    }

    fun getDependencyRoot(): DependencyRoot {
        return dependencyRoot
    }
}