package com.example.course999.core

import android.app.Application
import android.util.Log
import com.example.course999.main.MainRepresentative

class App() : Application() {
    lateinit var mainRepresentative: MainRepresentative
    private val handleDeath = HandleDeath.Base()
    override fun onCreate() {
        super.onCreate()
        mainRepresentative = MainRepresentative.Base(UiObservable.Single())
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