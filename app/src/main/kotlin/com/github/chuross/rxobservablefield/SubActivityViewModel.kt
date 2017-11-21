package com.github.chuross.rxobservablefield

import com.github.chuross.rxobservablefield.extension.toObservableField

class SubActivityViewModel(val textField: ObservableField<String>) {

    val textLength: ReadOnlyObservableField<Int> = textField.rx.map { it.length }.toObservableField()
}