package com.example.course999

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity {

    constructor() : super() {
        Log.d("nilsolk", "called from MainActivity constructor")
    }

    private val list = mutableListOf<Any>()
    private var bundleCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("nilsolk", "called from Main Activity OnCreate")
        setContentView(R.layout.activity_main)

        val textViewBundle = findViewById<TextView>(R.id.textViewBundle)
        bundleCounter = savedInstanceState?.getInt("myValue") ?: 0
        textViewBundle.text = bundleCounter.toString()

        findViewById<Button>(R.id.bundle_save_button).setOnClickListener {
            textViewBundle.text = (++bundleCounter).toString()
        }




        Log.d("nilsolk", "called from Main Activity OnCreate")
        findViewById<Button>(R.id.large_heap_button).setOnClickListener {
            while (true) {
                list.add(A())
            }
        }

        val textView = findViewById<TextView>(R.id.textView)
        val counter = (application as App).counter

        textView.text = counter.toString()

        findViewById<Button>(R.id.save_w_app_button).setOnClickListener {
            textView.text = (++(application as App).counter).toString()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("nilsolk", "data had save")
        outState.putInt("myValue", bundleCounter)
    }

    override fun onStart() {
        super.onStart()
        Log.d("nilsolk", "called from Main Activity OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("nilsolk", "called from Main Activity OnResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("nilsolk", "called from Main Activity onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("nilsolk", "called from Main Activity onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("nilsolk", "called from Main Activity onDestroy()")
    }

}

data class A(
    private val l: Long = System.currentTimeMillis(),
    private val b: Long = System.currentTimeMillis()
)