package test

open class Animal {
    open fun makeSound() {
        println("animal sound")
    }
}

class Dog : Animal() {
    override fun makeSound() {
        println("Woof Woof")
    }
}

class Cat : Animal() {
    override fun makeSound() {
        println("Meow Meow")
    }
}

fun main() {
    val d = Dog()
    val c = Cat()

    d.makeSound()
    c.makeSound()
}
