package com.github.chuross.rxobservablefield;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MainActivityViewModel {

    public RxObservableField<String> textField = new RxObservableField<>("");
    public ReadOnlyRxObservableField<Integer> lengthField = new ReadOnlyRxObservableField<>(textField.toObservable().map(new Function<String, Integer>() {
        @Override
        public Integer apply(@NonNull String text) throws Exception {
            return text.length();
        }
    }));
    public ReadOnlyRxObservableField<String> upperStringTextField = new ReadOnlyRxObservableField<>(textField.toObservable().map(new Function<String, String>() {
        @Override
        public String apply(@NonNull String text) throws Exception {
            return text.toUpperCase();
        }
    }));

}
