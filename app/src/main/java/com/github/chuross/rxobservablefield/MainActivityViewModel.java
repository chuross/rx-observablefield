package com.github.chuross.rxobservablefield;


import android.databinding.ObservableField;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MainActivityViewModel {

    public ObservableField<String> textField = new ObservableField<>("");
    public ReadOnlyRxObservableField<Integer> lengthField = getStringLengthObservableField(textField);

    private ReadOnlyRxObservableField<Integer> getStringLengthObservableField(ObservableField<String> field) {
        Observable<String> source = ObservableUtils.toObservable(field);

        Observable<Integer> lengthSource = source.map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String text) throws Exception {
                return text.length();
            }
        });

        return new ReadOnlyRxObservableField<>(lengthSource);
    }
}
