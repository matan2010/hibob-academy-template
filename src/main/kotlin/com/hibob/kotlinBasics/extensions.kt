package com.hibob.kotlinBasics

fun test(){
}

fun trs(){
    val m = listOf(1)
}

fun List<Int>.sum(): Int{
    var result=0
    for(i in this){
        result +=i
    }
    return result
}


fun main(args: Array<String>){
    val list = listOf(1,2,3)
    val sum= list.sum()
    println(sum)
    println(2.0.toPowerOf(3))
}

infix fun Double.toPowerOf(exponent:Int): Double{
    var ans = 1.0
    var n = this
    var exp = exponent
    var flag = true
    if(exponent < 0){
        exp = -exp
        flag = false
    }
    for (i in 1..exp){
        ans *=n
    }
    if(!flag)
        return 1/ans
    return ans
}