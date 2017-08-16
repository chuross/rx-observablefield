package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class ReadOnlyRxObservableField<T>(source: Observable<T>) : ObservableField<T>() {

    @Transient private val source: Observable<T> = source.doOnNext { super.set(it) }.share()
    private val callbacks: MutableMap<android.databinding.Observable.OnPropertyChangedCallback, Disposable> = mutableMapOf()

    @Synchronized
    override fun addOnPropertyChangedCallback(callback: android.databinding.Observable.OnPropertyChangedCallback) {
        super.addOnPropertyChangedCallback(callback)
        callbacks.put(callback, source.subscribe())
    }

    @Synchronized
    override fun removeOnPropertyChangedCallback(callback: android.databinding.Observable.OnPropertyChangedCallback) {
        callbacks[callback]?.takeIf { !it.isDisposed }?.dispose()
        super.removeOnPropertyChangedCallback(callback)
    }

    @Deprecated("This class is ReadOnly!", ReplaceWith("not call"))
    override fun set(value: T) {
        throw UnsupportedOperationException()
    }
}