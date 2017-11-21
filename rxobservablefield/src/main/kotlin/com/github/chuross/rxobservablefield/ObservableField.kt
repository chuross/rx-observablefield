package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import com.github.chuross.rxobservablefield.internal.ObservableUtils
import io.reactivex.Observable

class ObservableField<T> : ObservableField<T> {

    @Transient
    private var observable: Observable<T>? = null

    constructor(): super()

    constructor(default: T): super(default)

    fun toObservable(): Observable<T> = observable ?: newObservable().also { observable = it }

    private fun newObservable(): Observable<T> = ObservableUtils.toObservable(this).serialize()
}