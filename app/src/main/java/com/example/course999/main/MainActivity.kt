package com.example.course999.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.course999.R
import com.example.course999.core.App
import com.example.course999.core.UiObserver

class MainActivity : AppCompatActivity() {
    private lateinit var representative: MainRepresentative

    private lateinit var activityCallback: ActivityCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        representative = (application as App).mainRepresentative

        activityCallback = object : ActivityCallback {
            override fun update(data: Int) {

            }
        }
        representative.showDashboard(savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        representative.startGettingUpdates(activityCallback)
    }

    override fun onStop() {
        super.onStop()
        representative.stopGettingUpdates()
    }

}

interface ActivityCallback : UiObserver<Int>

