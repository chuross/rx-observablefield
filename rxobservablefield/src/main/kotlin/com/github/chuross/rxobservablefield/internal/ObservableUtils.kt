package com.github.chuross.rxobservablefield.internal

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import android.databinding.Observable.OnPropertyChangedCallback
import android.databinding.ObservableList

internal object ObservableUtils {

    @JvmStatic
    fun <T> toObservable(field: ObservableField<T>): Observable<T> {
        return Observable.create { emitter ->
            field.get()?.let { emitter.onNext(it) }

            val callback = object : OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: android.databinding.Observable?, propertyId: Int) {
                    emitter.onNext(field.get())
                }
            }

            field.addOnPropertyChangedCallback(callback)

            emitter.setDisposable(Disposables.fromAction { field.removeOnPropertyChangedCallback(callback) })
        }
    }

    @JvmStatic
    fun <T> toObservable(list: ObservableList<T>): Observable<List<T>> {
        return Observable.create { emitter ->
            emitter.onNext(list.toList())

            val callback = object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
                override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
                    emitter.onNext(list.toList())
                }

                override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
                    emitter.onNext(list.toList())
                }

                override fun onChanged(sender: ObservableList<T>?) {
                    emitter.onNext(list.toList())
                }

                override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
                    emitter.onNext(list.toList())
                }

                override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
                    emitter.onNext(list.toList())
                }
            }

            list.addOnListChangedCallback(callback)

            emitter.setDisposable(Disposables.fromAction { list.removeOnListChangedCallback(callback) })
        }
    }
}