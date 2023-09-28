package com.example.course999

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("currentThread", "${Thread.currentThread()}")


        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        val buttonTimer = findViewById<Button>(R.id.buttonTimer)

        var counter = 0L

        textView.setOnClickListener {
            Thread {
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    textView.text = "Getting from post method"
                    Toast.makeText(this, "Delayed on ${DELAY / 1000} sec", Toast.LENGTH_SHORT)
                        .show()
                }, DELAY)
            }.start()
        }

        button.setOnClickListener {
            object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    textView.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    textView.append("\nfinished")
                }

            }.start()
        }

        buttonTimer.setOnClickListener {
            Thread.sleep(10_000)
            Timer().scheduleAtFixedRate(object : TimerTask() {
                override fun run() = runOnUiThread {
                    textView.text = counter++.toString()
                }
            }, 0, 1000)

        }

    }


    companion object {
        private const val DELAY = 2000L
    }
}