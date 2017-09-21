package com.github.chuross.rxobservablefield

import com.github.chuross.rxobservablefield.extension.toReadOnlyRxObservableField

class SubActivityViewModel(val textField: ObservableField<String>) {

    val textLength: ReadOnlyObservableField<Int> = textField.toObservable().map { it.length }.toReadOnlyRxObservableField()
}