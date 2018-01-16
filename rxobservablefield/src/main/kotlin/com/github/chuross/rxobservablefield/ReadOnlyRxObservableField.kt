package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class ReadOnlyRxObservableField<T>(source: Observable<T>, disposables: CompositeDisposable) : ObservableField<T>() {

    @Transient private val source: Observable<T> = source.doOnNext { super.set(it) }.share()
    val rx: Observable<T> get() = source

    init {
        disposables.add(this.source.subscribe())
    }

    override fun get(): T? = super.get()

    fun or(default: T): T = get() ?: default

    @Deprecated("This class is ReadOnly!", ReplaceWith("not call"))
    override fun set(value: T) {
        throw UnsupportedOperationException()
    }
}