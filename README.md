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
    compile 'com.github.chuross.rx-observablefield:rx-observablefield:x.x.x'
}
```

## kotlin
```
val hogeField = ObservableField<String>()

// ObservableField -> Rx#Observable + Operators -> ReadOnlyObservableField
val hogeLengthField = hogeField.toObservable().map { it.length }.filter { it > 10 }.toReadOnlyObservableField()
```