package com.example.course999

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var representative: MainRepresentative


    private val activityCallback = object : ActivityCallback {
        override fun updateUi() =
            runOnUiThread {
                textView.setText(R.string.callback_from_ui_text)
            }

        override fun isEmpty(): Boolean = false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        representative = (application as App).mainRepresentative

        val button = findViewById<Button>(R.id.save_w_app_button)

        button.setOnClickListener {
            representative.startAsync()
        }
    }

    override fun onResume() {
        super.onResume()
        textView = findViewById(R.id.textView)
        representative.startGettingUpdates(activityCallback)
    }

    override fun onStop() {
        super.onStop()
        representative.stopGettingUpdates()
    }

    private val thread = Thread {
        Thread.sleep(3000)
        runOnUiThread {

        }
    }

}

interface ActivityCallback {
    fun updateUi()
    fun isEmpty(): Boolean
    class Empty : ActivityCallback {
        override fun updateUi() = Unit
        override fun isEmpty(): Boolean = true
    }
}

