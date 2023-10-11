package com.example.course999

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var representative: MainRepresentative

    private lateinit var activityCallback: ActivityCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        representative = (application as App).mainRepresentative

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.save_w_app_button)

        activityCallback = object : ActivityCallback {
            override fun update(data: Int) = runOnUiThread {
                textView.setText(data)
            }
        }

        button.setOnClickListener {
            representative.startAsync()
        }
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

