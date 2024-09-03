package com.hibob.kotlinBasics//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
        if (a == b)  return true  else return false
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

class StringValidator {

    fun isValidIdentifier(s: String): Boolean {
        TODO()
    }

    fun main(args: Array<String>) {
        println(isValidIdentifier("name"))   // true
        println(isValidIdentifier("_name"))  // true
        println(isValidIdentifier("_12"))    // true
        println(isValidIdentifier(""))       // false
        println(isValidIdentifier("012"))    // false
        println(isValidIdentifier("no$"))    // false
    }

}

fun isValidIdentifier(s: String): Boolean {
    if(s.length < 1) return false
    val a = s.get(0)
    if('0'<= a && '9' >= a) return false
    for (i in s){
       if(!((i=='_'||'A'<= i && 'Z'>= i) || ('a'<= i && 'z'>= i )||('0'<= i && '9'>= i) ) ) return false
    }
    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false



    println("kotlin" in "Java".."Scala")
    println("kotlin" in setOf("Java", "Scala"))
}




