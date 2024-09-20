package com.hibob.academy.service

import com.hibob.academy.dao.PetDao
import com.hibob.academy.dao.PetData
import com.hibob.academy.dao.PetType
import jakarta.ws.rs.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PetService @Autowired constructor(private val petDao : PetDao){

    fun getPetsByType(companyId:Long, type: String): List<PetData> {
        if (!isValidPetType(type)){
            throw BadRequestException("Invalid PetType: $type")
        }
        return petDao.getPetsByType(companyId, PetType.fromDatabaseValue(type))
    }

    fun insertPet(name: String, type: String,companyId: Long ,ownerId:Long?) {
        if (!isValidPetType(type)){
            throw BadRequestException("Invalid PetType: $type")
        }
        return petDao.insertPet(name, PetType.fromDatabaseValue(type),companyId,ownerId)
    }

    fun adoptPet(petId: Long, ownerId: Long,companyId :Long) {
        return petDao.adoptPet(petId,ownerId,companyId)
    }

    fun countPetsByType(companyId :Long): Map<String, Int>  {
        return petDao.countPetsByType(companyId)
    }

    fun getPetsByOwnerId(ownerId: Long,companyId :Long): List<PetData> {
        return petDao.getPetsByOwnerId(ownerId,companyId)
    }

    private fun isValidPetType(value: String): Boolean {
        return enumValues<PetType>().any { it.name.equals(value, ignoreCase = true) }
    }
}