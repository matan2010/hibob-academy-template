package com.hibob.academy.dao

import java.time.LocalDate
import java.util.Date

data class Example(val id: Int, val companyId: Long, val data: String)

data class PetData(val name: String, val dateOfArrival:LocalDate, val companyId: Long)

data class OwnerData(val name: String, val employeeId:Long, val companyId: Long)

enum class PetType{
    Dog,
    Cat
}