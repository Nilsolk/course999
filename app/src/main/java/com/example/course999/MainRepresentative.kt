package com.example.course999

interface MainRepresentative {

    fun startAsync()
    fun startGettingUpdates(callback: ActivityCallback)
    fun stopGettingUpdates()

    class Base : MainRepresentative {

        private var callback: ActivityCallback = ActivityCallback.Empty()
        private var needToPing = false

        private val thread = Thread {
            Thread.sleep(5000)
            if (callback.isEmpty()) {
                needToPing = false
            } else {
                callback.updateUi()
            }
        }

        override fun startAsync() {
            thread.start()
        }

        override fun startGettingUpdates(callback: ActivityCallback) {
            if (needToPing) {
                callback.updateUi()
                needToPing = false
            } else this.callback = callback
        }

        override fun stopGettingUpdates() {
            this.callback = ActivityCallback.Empty()
        }

    }

}