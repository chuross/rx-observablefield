package com.github.chuross.rxobservablefield;

import android.databinding.ObservableField;

public class SubActivityViewModel {

    public ObservableField<String> textField;

    public SubActivityViewModel(ObservableField<String> textField) {
        this.textField = textField;
    }
}
