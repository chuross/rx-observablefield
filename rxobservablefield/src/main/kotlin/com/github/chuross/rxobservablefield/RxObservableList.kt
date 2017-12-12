package com.github.chuross.rxobservablefield

import android.databinding.ObservableArrayList
import com.github.chuross.rxobservablefield.internal.ObservableUtils
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxObservableList<T> : ObservableArrayList<T>() {

    @Transient private var observable: Observable<List<T>>? = null
    @Transient private var emptyEvent: PublishSubject<List<T>>? = null
    val rx: Observable<List<T>> get() = observable ?: Observable.merge(listOf(
            ObservableUtils.toObservable(this).also { observable = it },
            PublishSubject.create<List<T>>().also { emptyEvent = it }
    ))

    override fun addAll(elements: Collection<T>): Boolean {
        return super.addAll(elements).also {
            if (isEmpty()) emptyEvent?.onNext(toList())
        }
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        return super.addAll(index, elements).also {
            if (isEmpty()) emptyEvent?.onNext(toList())
        }
    }
}