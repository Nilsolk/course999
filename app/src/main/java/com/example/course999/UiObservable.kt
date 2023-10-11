package com.example.course999

interface UiObservable<T : Any> : UiUpdate<T> {

    fun updateObserver(uiObserver: UiObserver<T> = UiObserver.Empty())

    class Base<T : Any> : UiObservable<T> {

        private var cache: T? = null

        private var observer: UiObserver<T> = UiObserver.Empty()

        override fun updateObserver(uiObserver: UiObserver<T>) {
            observer = uiObserver
            if (!observer.isEmpty()) {
                cache?.let {
                    observer.update(it)
                }
            }
        }

        override fun update(data: T) {
            if (observer.isEmpty()) {
                cache = data
            } else {
                cache = data
                observer.update(data)
            }
        }


    }

    class Single<T : Any> : UiObservable<T> {

        private var cache: T? = null

        private var observer: UiObserver<T> = UiObserver.Empty()

        override fun updateObserver(uiObserver: UiObserver<T>) {
            observer = uiObserver
            if (!observer.isEmpty()) {
                cache?.let {
                    observer.update(it)
                    cache = null
                }
            }
        }

        override fun update(data: T) {
            if (observer.isEmpty()) {
                cache = data
            } else {
                cache = null
                observer.update(data)
            }
        }


    }
}


interface UiUpdate<T : Any> {
    fun update(data: T)
}

interface UiObserver<T : Any> : UiUpdate<T> {

    fun isEmpty(): Boolean = false

    class Empty<T : Any> : UiObserver<T> {
        override fun update(data: T) = Unit
        override fun isEmpty(): Boolean = false

    }
}