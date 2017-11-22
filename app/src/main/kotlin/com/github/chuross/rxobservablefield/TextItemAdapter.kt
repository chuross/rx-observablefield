package com.github.chuross.rxobservablefield

import android.content.Context
import android.databinding.ObservableList
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.chuross.recyclerviewadapters.databinding.BindingItemAdapter
import com.github.chuross.rxobservablefield.databinding.ViewTextBinding

class TextItemAdapter(context: Context, source: ObservableList<String>) : BindingItemAdapter<String, BindingViewHolder<ViewTextBinding>>(context, source) {

    override fun getAdapterId(): Int = R.layout.view_text

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BindingViewHolder<ViewTextBinding> {
        return BindingViewHolder(ViewTextBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewTextBinding>?, position: Int) {
        holder?.binding?.text = get(position)
    }
}