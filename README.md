# Goal

kotlin

```
val hogeField = ObservableField<String>()

// ObservableField -> Rx#Observable + Operators -> ReadOnlyObservableField
val hogeLengthField = hogeField.toObservable().map { it.length }.filter { it > 10 }.toReadOnlyObservableField()

// bind data
val dataBinding = DataBindingUtils.setContentView(this, R.layout.your_activity)
dataBinding.hogeText = hogeField
dataBinding.hogeLengthText = hogeLengthField
```