package com.github.chuross.rxobservablefield

import android.databinding.ListChangeRegistry
import android.databinding.ObservableList
import com.github.chuross.rxobservablefield.internal.ObservableUtils
import io.reactivex.Observable

class RxObservableList<T> : ArrayList<T>(), ObservableList<T> {

    @Transient private var observable: Observable<List<T>>? = null
    @Transient private var listeners: ListChangeRegistry? = ListChangeRegistry()
    val rx: Observable<List<T>> get() = observable ?: ObservableUtils.toObservable(this).also { observable = it }

    override fun addOnListChangedCallback(callback: ObservableList.OnListChangedCallback<out ObservableList<T>>?) {
        if (listeners == null) listeners = ListChangeRegistry()
        listeners?.add(callback)
    }

    override fun removeOnListChangedCallback(callback: ObservableList.OnListChangedCallback<out ObservableList<T>>?) {
        listeners?.remove(callback)
    }

    override fun add(element: T): Boolean {
        super.add(element)
        notifyAdd(size - 1, 1)
        return true
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        notifyAdd(index, 1)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val oldSize = size
        val added = super.addAll(elements)
        if (added) {
            notifyAdd(oldSize, size - oldSize)
        }
        return added
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val added = super.addAll(index, elements)
        if (added) {
            notifyAdd(index, elements.size)
        }
        return added
    }

    override fun clear() {
        val oldSize = size
        super.clear()
        if (oldSize != 0) {
            notifyRemove(0, oldSize)
        }
    }

    override fun removeAt(index: Int): T {
        val value = super.removeAt(index)
        notifyRemove(index, 1)
        return value
    }

    override fun remove(element: T): Boolean {
        val index = indexOf(element)
        if (index >= 0) {
            removeAt(index)
            return true
        } else {
            return false
        }
    }

    override fun set(index: Int, element: T): T {
        val value = super.set(index, element)
        listeners?.notifyChanged(this, index, 1)
        return value
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        notifyRemove(fromIndex, toIndex - fromIndex)
    }

    private fun notifyAdd(start: Int, count: Int) {
        listeners?.notifyInserted(this, start, count)
    }

    private fun notifyRemove(start: Int, count: Int) {
        listeners?.notifyRemoved(this, start, count)
    }

    fun forceNotify() {
        listeners?.notifyChanged(this)
    }

}