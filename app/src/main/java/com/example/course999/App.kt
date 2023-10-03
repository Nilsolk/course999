package com.example.course999

import android.app.Application
import android.util.Log

class App() : Application() {
    init {
        Log.d("nilsolk", "called from constructor")
    }

    var counter = 0

    override fun onCreate() {
        super.onCreate()
        Log.d("nilsolk", "called from onCreate()")

    }
}