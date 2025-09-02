### SAM Interface
A functional interface in Kotlin is an interface that contains only one abstract method, allowing it to be instantiated using a lambda expression. This makes the code more concise and readable, as you can create instances of the interface without needing to implement the method in a separate class
### Definition of Functional Interfaces
A functional interface in Kotlin is an interface that contains exactly one abstract method. This is also known as a Single Abstract Method (SAM) interface. While it can have multiple non-abstract methods, only one method can be abstract.
```
fun interface Greeter {
    fun greet(name: String)
}
```
```
val greeter: Greeter = Greeter { name -> 
    println("Hello, $name!")
}
```
```
greeter.greet("Kotlin")
```
### SAM Conversions
Kotlin supports SAM conversions, allowing you to pass lambda expressions directly where a functional interface is expected. This feature enhances code readability and conciseness.
```
fun runAction(action: Greeter) {
    action.greet("SAM Conversion")
}

fun main() {
    runAction { name -> 
        println("Running action with $name")
    }
}
```
### Differences from Type Aliases
Functional interfaces and type aliases serve different purposes. A type alias is just a name for an existing type and does not create a new type. In contrast, a functional interface can have multiple non-abstract methods and can be extended.

| FEATURE                | FUNCTIONAL INTERFACE | TYPE ALIAS |
|------------------------|----------------------|------------|
| Abstract Methods       | One (exactly)        | None       |
| Non-Abstract Methods   | Multiple             | None       |
| New Type Creation      | Yes                  | No         |
| Extension Capabilities | Yes                  | No         |
