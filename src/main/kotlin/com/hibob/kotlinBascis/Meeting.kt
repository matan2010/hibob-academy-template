package com.hibob.kotlinBascis


abstract class Meeting(val name:String,val location:Location){
    constructor(name: String, location: Location, participants: List<Participant>) : this(name, location) {
    }
}

abstract class Location{
    abstract val street: String
    abstract val city: String
    abstract val county: String
}

class US(override val street: String, override val city: String, override val county: String ,val zipCode:Int, ):Location()
class UK(override val street: String, override val city: String, override val county: String ,val postcode:Int, ):Location()

//class PersonalReview(override val name: String, override val location:Location):Meeting(name,location)

