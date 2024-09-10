package com.hibob.academy.dao

import java.util.Date

data class Example(val id: Long, val companyId: Long, val data: String)

data class PetData(val name: String, val type: String, val companyId: Int,val dateOfArrival:Date)