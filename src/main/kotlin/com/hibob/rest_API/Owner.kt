package com.hibob.rest_API


data class oldOwner (val ownerId:Long,val name :String,val companyId:Long,val employeeId: Long)

data class Owner(val ownerId: Long,val firstName: String,val lastName: String,val companyId:Long,val employeeId: Long)