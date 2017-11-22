package com.github.chuross.rxobservablefield

import com.github.chuross.rxobservablefield.extension.toObservableField

class SubActivityViewModel(val textField: RxObservableField<String>) {

    val textLength: ReadOnlyRxObservableField<Int> = textField.rx.map { it.length }.toObservableField()
}