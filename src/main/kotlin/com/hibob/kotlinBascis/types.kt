import java.time.LocalDate

//1. create data class of Cart that include: client Id and list of Products
//2. product object contain id, name, price and custom - custom can be either int, string or any other type
//3. create sealed class of payment that can be use by the following classes:
//    CreditCard - contains number, expiryDate, type and limit (type can be VISA, MASTERCARD, DISCOVER and AMERICAN_EXPRESS)
//    PayPal - contain email
//    Cash - without args
//4. add fail function that get message an argument and throw IllegalStateException
//5. write function check if custom is true and only if its true its valid product.
//6. write function called checkout and get cart and payment that pay the money
//   * only custom with true are valid
//   * cash payment is not valid to pay so if the payment is cash use fail function
//   * in case of credit card need to validate the expiryDate is after the current date + limit is bigger than the total we need to pay and we allow to use only  VISA or MASTERCARD
//   * in case of payPal validate we hae @
///  * the return value of this function, should be a data class with employee id, status (success or failed) and total called Check
// 7. implement pay method

class Product(val id: String, val name: String,val price: Double,val custom:Any)
data class Cart(val clientId: String, val products:List<Product> )
sealed class Payment(){
    class CreditCard(val number:String,val expiryDate: LocalDate ,val type:Type,val limit:Double) : Payment()
    class PayPal(val email:String)
    class Cash()
}

enum class Type {
    VISA, MASTERCARD, DISCOVER ,AMERICAN_EXPRESS
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

fun checkout(cart:Cart, payment:Payment){
    var total:Double = 0.0
    cart.products.forEach()
}

data class Check(val employeeId:String,val status: Status,val total:Double)

enum class Status(){
    Success,Failure
}
