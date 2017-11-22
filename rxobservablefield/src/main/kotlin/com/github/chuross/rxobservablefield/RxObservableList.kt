package com.github.chuross.rxobservablefield

import android.databinding.ObservableArrayList
import com.github.chuross.rxobservablefield.internal.ObservableUtils
import io.reactivex.Observable

class RxObservableList<T> : ObservableArrayList<T>() {

    @Transient
    private var observable: Observable<List<T>>? = null
    val rx: Observable<List<T>> get() = observable ?: ObservableUtils.toObservable(this).share().also { observable = it }

}