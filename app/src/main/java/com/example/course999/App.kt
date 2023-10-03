package com.example.course999

import android.app.Application
import android.util.Log

class App() : Application() {
    init {
        Log.d("nilsolk", "called from constructor")
    }

    private val handleDeath = HandleDeath.Base()

    var counter = 0

    override fun onCreate() {
        super.onCreate()
        Log.d("nilsolk", "called from onCreate()")

    }

    fun activityCreated(firstOpening: Boolean) {
        if (firstOpening) {
            Log.d("nilsolk", "first time opened app")
            handleDeath.firstOpening()
        } else {
            if (handleDeath.wasDeathHappened()) {
                Log.d("nilsolk", "dead happened")
                handleDeath.deathHandled()
            } else {
                Log.d("nilsolk", "Activity recreated")
            }
        }
    }
}