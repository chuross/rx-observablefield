package com.github.chuross.rxobservablefield;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.chuross.rxobservablefield.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MainActivityViewModel viewModel = new MainActivityViewModel();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);

        binding.goSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra(SubActivity.EXTRA_KEY_TEXT_FIELD, viewModel.textField);
                startActivity(intent);
            }
        });
    }
}
