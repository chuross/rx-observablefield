package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class ReadOnlyRxObservableField<T>(source: Observable<T>) : ObservableField<T>(), Disposable {

    @Transient private val source: Observable<T> = source.filter { !isDisposed }.doOnNext { super.set(it) }.share()
    var disposable: Disposable? = null
    val rx: Observable<T> get() = source

    init {
        disposable = this.source.subscribe()
    }

    override fun get(): T? = super.get()

    fun or(default: T): T = get() ?: default

    @Deprecated("This class is ReadOnly!", ReplaceWith("not call"))
    override fun set(value: T) {
        throw UnsupportedOperationException()
    }

    override fun isDisposed(): Boolean = disposable == null

    override fun dispose() {
        disposable?.dispose()?.also { disposable = null }
    }
}