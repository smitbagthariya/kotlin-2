package test

fun main() {
    var sum = 0
    for (i in 1..100) {
        if (i % 2 == 0) {
            sum += i
        }
    }
    println("Sum of even number 1 and 100 : $sum")
}
