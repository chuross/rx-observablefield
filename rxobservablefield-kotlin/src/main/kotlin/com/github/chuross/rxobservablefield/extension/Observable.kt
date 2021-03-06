package com.github.chuross.rxobservablefield.extension

import com.github.chuross.rxobservablefield.ReadOnlyRxObservableField
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

fun <T> Observable<T>.toObservableField(disposables: CompositeDisposable, default: T? = null): ReadOnlyRxObservableField<T> {
    return ReadOnlyRxObservableField(this, disposables, default)
}