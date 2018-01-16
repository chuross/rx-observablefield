package com.github.chuross.rxobservablefield;


import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

public class MainActivityViewModel {

    private CompositeDisposable disposables = new CompositeDisposable();
    public RxObservableField<String> textField = new RxObservableField<>("");
    public ReadOnlyRxObservableField<Integer> lengthField = new ReadOnlyRxObservableField<>(textField.getRx().map(new Function<String, Integer>() {
        @Override
        public Integer apply(@NonNull String text) throws Exception {
            return text.length();
        }
    }));
    public ReadOnlyRxObservableField<String> upperStringTextField = new ReadOnlyRxObservableField<>(textField.getRx().map(new Function<String, String>() {
        @Override
        public String apply(@NonNull String text) throws Exception {
            return text.toUpperCase();
        }
    }));

    MainActivityViewModel() {
        disposables.addAll(lengthField, upperStringTextField);
    }

    public void destroy() {
        disposables.dispose();
    }

}
