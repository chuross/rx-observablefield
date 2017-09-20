package com.github.chuross.rxobservablefield

import com.github.chuross.rxobservablefield.extension.toReadOnlyRxObservableField

class SubActivityViewModel(val textField: RxObservableField<String>) {

    val textLength: ReadOnlyRxObservableField<Int> = textField.toObservable().map { it.length }.toReadOnlyRxObservableField()
}