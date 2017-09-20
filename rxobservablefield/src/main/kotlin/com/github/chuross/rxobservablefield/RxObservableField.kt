package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import com.github.chuross.rxobservablefield.internal.ObservableUtils
import io.reactivex.Observable

class RxObservableField<T> : ObservableField<T> {

    constructor(): super()

    constructor(default: T): super(default)

    fun toObservable(): Observable<T> = ObservableUtils.toObservable(this)
}