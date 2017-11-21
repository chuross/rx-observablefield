package com.github.chuross.rxobservablefield;


import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class MainActivityViewModel {

    public ObservableField<String> textField = new ObservableField<>("");
    public ReadOnlyObservableField<Integer> lengthField = new ReadOnlyObservableField<>(textField.getRx().map(new Function<String, Integer>() {
        @Override
        public Integer apply(@NonNull String text) throws Exception {
            return text.length();
        }
    }));
    public ReadOnlyObservableField<String> upperStringTextField = new ReadOnlyObservableField<>(textField.getRx().map(new Function<String, String>() {
        @Override
        public String apply(@NonNull String text) throws Exception {
            return text.toUpperCase();
        }
    }));

}
