[![](https://jitpack.io/v/chuross/rx-observablefield.svg)](https://jitpack.io/#chuross/rx-observablefield)

# Rx ObservableField

DataBinding converter library for RxJava2.
This Library provide ObservableField to convert RxJava2.

## Download
1. add JitPack repository to your project root build.gradle.
```
repositories {
    maven { url "https://jitpack.io" }
}
```

2. add the dependency latest version: [![](https://jitpack.io/v/chuross/rx-observablefield.svg)](https://jitpack.io/#chuross/rx-observablefield)
```
dependencies {
    compile 'com.github.chuross.rx-observablefield:rxobservablefield:x.x.x'
}
```

3. (optional) add the dependency for kotlin: [![](https://jitpack.io/v/chuross/rx-observablefield.svg)](https://jitpack.io/#chuross/rx-observablefield)
```
dependencies {
    compile 'com.github.chuross.rx-observablefield:rxobservablefield-kotlin:x.x.x'
}
```

## Usage
### kotlin
```
val hogeField = RxObservableField<String>()

// ObservableField -> Rx#Observable + Operators -> ReadOnlyRxObservableField
val hogeLengthField = hogeField.rx.map { it.length }.filter { it > 10 }.toObservableField() // ReadOnly!


// get value
hogeField.get() // String?
hogeField.or("fuga") // String
```
