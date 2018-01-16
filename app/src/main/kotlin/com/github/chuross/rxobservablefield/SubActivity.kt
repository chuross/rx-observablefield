package com.github.chuross.rxobservablefield

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.chuross.rxobservablefield.databinding.ActivitySubBinding

class SubActivity : Activity() {

    var viewModel: SubActivityViewModel? = null

    companion object {
        @JvmField
        val EXTRA_KEY_TEXT_FIELD = "extra_key_text_field"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textField = intent.getSerializableExtra(EXTRA_KEY_TEXT_FIELD) as RxObservableField<String>
        viewModel = SubActivityViewModel(textField)

        val viewModel = viewModel ?: return

        val binding = DataBindingUtil.setContentView<ActivitySubBinding>(this, R.layout.activity_sub)
        binding.viewModel = viewModel

        binding.deleteButton.setOnClickListener { viewModel.removeLastItem() }

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = TextItemAdapter(this, viewModel.textItems)
    }

    override fun onDestroy() {
        viewModel?.destroy()
        super.onDestroy()
    }
}