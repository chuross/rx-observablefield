## kotlin
```
val hogeField = ObservableField<String>()

// ObservableField -> Rx#Observable + Operators -> ReadOnlyObservableField
val hogeLengthField = hogeField.toObservable().map { it.length }.filter { it > 10 }.toReadOnlyObservableField()
```