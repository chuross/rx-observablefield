package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import android.databinding.Observable.OnPropertyChangedCallback

class ReadOnlyRxObservableField<T>(source: Observable<T>) : ObservableField<T>() {

    @Transient private val source: Observable<T> = source.doOnNext { super.set(it) }
    @Transient private val callbacks: MutableMap<OnPropertyChangedCallback, Disposable> = mutableMapOf()
    val rx: Observable<T> get() = source

    @Synchronized
    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        super.addOnPropertyChangedCallback(callback)
        callbacks.put(callback, source.subscribe())
    }

    @Synchronized
    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        callbacks[callback]?.takeIf { !it.isDisposed }?.dispose()
        super.removeOnPropertyChangedCallback(callback)
    }


    override fun get(): T? = super.get()

    fun or(default: T): T = get() ?: default

    @Deprecated("This class is ReadOnly!", ReplaceWith("not call"))
    override fun set(value: T) {
        throw UnsupportedOperationException()
    }
}