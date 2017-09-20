package com.github.chuross.rxobservablefield.extension

import com.github.chuross.rxobservablefield.ReadOnlyRxObservableField
import io.reactivex.Observable

fun <T> Observable<T>.toReadOnlyRxObservableField(): ReadOnlyRxObservableField<T> {
    return ReadOnlyRxObservableField(this)
}