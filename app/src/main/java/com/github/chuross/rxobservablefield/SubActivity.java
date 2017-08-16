package com.github.chuross.rxobservablefield;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.chuross.rxobservablefield.databinding.ActivitySubBinding;

public class SubActivity extends Activity {

    public static final String EXTRA_KEY_TEXT_FIELD = "extra_key_text_field";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ObservableField<String> textField = (ObservableField<String>) getIntent().getSerializableExtra(EXTRA_KEY_TEXT_FIELD);

        ActivitySubBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sub);
        binding.setViewModel(new SubActivityViewModel(textField));
    }
}
