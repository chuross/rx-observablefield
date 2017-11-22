package com.github.chuross.rxobservablefield

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class BindingViewHolder<out B: ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)