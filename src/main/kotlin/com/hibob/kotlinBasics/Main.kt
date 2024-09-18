package com.hibob.kotlinBasics

fun main() {
    fun helloWorld() {
        println("hello world")
    }

    fun evenOrOdd(a: Int) {
        if (a % 2 == 0) println("even") else println("odd")
    }

    fun isEqual(a: Int, b: Int): Boolean = a == b

    fun max(a: Int, b: Int): Int = if (a > b) a else b

    fun multiplication(a: Int = 1, b: Int = 1): Int = a * b
    multiplication(a = 1, b = 2)
    multiplication(b = 2)
    multiplication(a = 3)
    multiplication()
    multiplication(b = 2, a = 4)
}

fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty()) return false
    if (s[0].isDigit()) return false

    for (char in s) {
        if (!char.isLetterOrDigit() && char != '_') return false
    }
    return true
}





