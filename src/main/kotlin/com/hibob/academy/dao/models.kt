package com.hibob.academy.dao

import java.util.Date

data class Example(val id: Int, val companyId: Long, val data: String)

data class PetData(val name: String, val type: String, val companyId: Int,val dateOfArrival:java.time.LocalDate?)
