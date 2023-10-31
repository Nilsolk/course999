package com.example.course999

import androidx.annotation.MainThread

@MainThread
interface MainRepresentative {

    fun startAsync()
    fun startGettingUpdates(callback: UiObserver<Int>)
    fun stopGettingUpdates()
    fun saveState()

    class Base(private val observable: UiObservable<Int>) : MainRepresentative {


        private fun thread() = Thread {
            Thread.sleep(5000)
            observable.update(R.string.callback_from_ui_text)
        }

        override fun startAsync() {
            thread().start()
        }

        override fun startGettingUpdates(callback: UiObserver<Int>) =
            observable.updateObserver(callback)


        override fun stopGettingUpdates() = observable.updateObserver()


        override fun saveState() {
            TODO("Not yet implemented")
        }

    }

}