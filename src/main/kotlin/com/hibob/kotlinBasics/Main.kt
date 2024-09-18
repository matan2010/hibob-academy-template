package com.hibob.kotlinBasics
fun main() {
    //“hello world” program
    fun helloWorld() {
        println("hello world")
    }
    //function that prints if the given number is even or odd
    fun evenOrOdd(a: Int){
        if (a % 2 == 0) println("even") else println("odd")
    }
    //function that checks whether two integers are equal or not
    fun isEqual(a: Int ,b:Int):Boolean {
        return a == b
    }
    //function as an expression (one line) that prints the max value of two given integers
    fun max(a:Int ,b:Int):Int = if (a > b) a else b

    fun multiplication(a: Int = 1, b: Int = 1):Int = a * b
    //many possibilities to call fun multiplication
    multiplication(a=1, b=2)
    multiplication(b=2)
    multiplication(a=3)
    multiplication()
    multiplication(b=2,a=4)
}






