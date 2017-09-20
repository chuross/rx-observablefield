package com.github.chuross.rxobservablefield

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import android.databinding.Observable.OnPropertyChangedCallback

object ObservableUtils {

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
}