package com.github.chuross.rxobservablefield.extension

import com.github.chuross.rxobservablefield.ReadOnlyObservableField
import io.reactivex.Observable

fun <T> Observable<T>.toObservableField(): ReadOnlyObservableField<T> {
    return ReadOnlyObservableField(this)
}