package com.example.course999.main

import androidx.annotation.MainThread
import com.example.course999.R
import com.example.course999.core.UiObservable
import com.example.course999.core.UiObserver

@MainThread
interface MainRepresentative {

    fun showDashboard(firstTime: Boolean)
    fun startGettingUpdates(callback: UiObserver<Int>)
    fun stopGettingUpdates()

    class Base(private val observable: UiObservable<Int>) : MainRepresentative {


        private fun thread() = Thread {
            Thread.sleep(5000)
            observable.update(R.string.callback_from_ui_text)
        }

        override fun showDashboard(firstTime: Boolean) {
            if(firstTime) observable.update()
        }

        override fun startGettingUpdates(callback: UiObserver<Int>) =
            observable.updateObserver(callback)


        override fun stopGettingUpdates() = observable.updateObserver()


    }

}