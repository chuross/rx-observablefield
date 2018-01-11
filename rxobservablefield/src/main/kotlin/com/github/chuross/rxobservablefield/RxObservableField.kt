package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import com.github.chuross.rxobservablefield.internal.ObservableUtils
import io.reactivex.Observable

class RxObservableField<T> : ObservableField<T> {

    @Transient private var observable: Observable<T>? = null
    @Transient private var valueFilter: ((T) -> Boolean)? = null
    internal val ignoreLatestOnSubscribe: Boolean
    val rx: Observable<T> get() = observable ?: ObservableUtils.toObservable(this).also { observable = it }

    constructor(): super() {
        ignoreLatestOnSubscribe = false
    }

    constructor(default: T, ignoreLatestOnSubscribe: Boolean = false): super(default) {
        this.ignoreLatestOnSubscribe = ignoreLatestOnSubscribe
    }

    override fun get(): T? = super.get()

    fun or(default: T): T = get() ?: default

    override fun set(value: T) {
        valueFilter?.let {
            if (it.invoke(value)) super.set(value)
        } ?: super.set(value)
    }

    fun setValueFilter(valueFilter: ((T) -> Boolean)): RxObservableField<T> {
        return also { it.valueFilter = valueFilter }
    }

}