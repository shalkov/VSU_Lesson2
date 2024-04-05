package ru.shalkoff.vsu_lesson2_2024.application

import android.app.Application
import android.content.Intent
import android.util.Log

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("App", "Вызвался метод onCreate()")
    }
}