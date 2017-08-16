package com.github.chuross.rxobservablefield;


import android.databinding.ObservableField;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MainActivityViewModel {

    public ObservableField<String> textField = new ObservableField<>("");
    public ReadOnlyRxObservableField<Integer> lengthField = getStringLengthObservableField(textField);
    public ReadOnlyRxObservableField<String> upperStringTextField = getUpperStringTextObservableField(textField);

    private ReadOnlyRxObservableField<Integer> getStringLengthObservableField(ObservableField<String> field) {
        Observable<String> source = ObservableUtils.toObservable(field);

        Observable<Integer> modifiedSource = source.map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String text) throws Exception {
                return text.length();
            }
        });

        return new ReadOnlyRxObservableField<>(modifiedSource);
    }

    private ReadOnlyRxObservableField<String> getUpperStringTextObservableField(ObservableField<String> field) {
        Observable<String> source = ObservableUtils.toObservable(field);

        Observable<String> modifiedSource = source.map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String text) throws Exception {
                return text.toUpperCase();
            }
        });

        return new ReadOnlyRxObservableField<>(modifiedSource);
    }

}
