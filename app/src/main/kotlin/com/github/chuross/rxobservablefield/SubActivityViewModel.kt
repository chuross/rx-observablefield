package com.github.chuross.rxobservablefield

import com.github.chuross.rxobservablefield.extension.toObservableField
import io.reactivex.disposables.CompositeDisposable

class SubActivityViewModel(val textField: RxObservableField<String>) {

    val textLength: ReadOnlyRxObservableField<Int> = textField.rx.map { it.length }.toObservableField()
    val textItems: RxObservableList<String> = RxObservableList()
    private val disposables: CompositeDisposable = CompositeDisposable()

    init {
        textLength.rx
                .filter { it > 0 }
                .filter { it % 5 == 0 }
                .subscribe({
                    addItem(textField.get() ?: "")
                }).also { disposables.add(it) }
    }

    fun addItem(text: String) {
        textItems.add(text)
    }

    fun removeLastItem() {
        textItems.takeIf { textItems.isNotEmpty() }?.let { it.removeAt(it.lastIndex) }
    }
}