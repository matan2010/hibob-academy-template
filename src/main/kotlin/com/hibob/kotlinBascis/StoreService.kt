package com.hibob.kotlinBascis
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class StoreService {

    fun pay(cart: List<Cart>, payment: Payment): Map<String, Check> {
        val ans = mutableMapOf<String, Check>()
        cart.forEach{myCart->
            val check=checkout(myCart, payment)
            val clientId:String=myCart.clientId
            ans[clientId]=check
        }
       return ans
    }
}


fun checkCardType(type: CreditCardType):Boolean{
    return when(type){
        CreditCardType.MASTERCARD -> true
        CreditCardType.VISA -> true
        CreditCardType.DISCOVER -> false
        CreditCardType.AMERICAN_EXPRESS -> false
    }
}

fun checkPayment(payment: Payment, total: Double): Statuses {
    return when(payment){
        is Payment.CreditCard -> creditCardTest(payment,total)
        is Payment.PayPal-> payPalTest(payment)
        is Payment.Cash -> fail("It is not allowed to pay in cash")
    }
}

fun checkout(cart: Cart, payment: Payment): Check {
    var total:Double = 0.0
    cart.products.forEach {product ->
        if(checkCustom(product.custom)){
            total += product.price
        }
    }
    val status =checkPayment(payment,total)
    if(status.equals(Statuses.FAILURE)){
        total = 0.0
    }
    return Check(cart.clientId,status,total)
}

fun fail(message:String):Nothing{
    throw IllegalStateException(message)
}

fun checkCustom(custom: Any):Boolean{
    return when(custom){
        is Boolean-> custom
        else->false

    }
}

fun creditCardTest(payment: Payment.CreditCard, total: Double): Statuses {
    if(payment.expiryDate> LocalDate.now() &&
        payment.limit>total &&
        (payment.number.length==10) &&
        checkCardType(payment.type)){
        return Statuses.SUCCESS
    } else return Statuses.FAILURE
}
fun payPalTest(payment: Payment.PayPal): Statuses {
    if (payment.email.contains("@")){
        return Statuses.SUCCESS
    } else return Statuses.FAILURE
}
fun main() {
    println("sds")
}