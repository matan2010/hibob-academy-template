package com.hibob.academy.service

import com.hibob.academy.dao.Pet
import com.hibob.academy.dao.PetDao
import com.hibob.academy.dao.PetData
import com.hibob.academy.dao.PetType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PetService @Autowired constructor(private val petDao: PetDao) {

    fun getPetsByType(companyId: Long, type: String): List<PetData> {
        if (companyId < 0) {
            throw IllegalArgumentException("Invalid companyId")
        }
        if (!isValidPetType(type)) {
            throw IllegalArgumentException("Invalid PetType: $type")
        }
        return petDao.getPetsByType(companyId, PetType.fromDatabaseValue(type))
    }

    fun insertPet(name: String, type: String, companyId: Long, ownerId: Long?) {
        if (name == "") {
            throw IllegalArgumentException("Name cannot be empty")
        }
        if (companyId < 0) {
            throw IllegalArgumentException("Invalid companyId")
        }
        if (ownerId != null && ownerId < 0) {
            throw IllegalArgumentException("Invalid ownerId")
        }
        if (!isValidPetType(type)) {
            throw IllegalArgumentException("Invalid PetType: $type")
        }

        return petDao.insertPet(name, PetType.fromDatabaseValue(type), companyId, ownerId)
    }

    fun adoptPet(petId: Long, ownerId: Long, companyId: Long) {
        if (petId < 0) {
            throw IllegalArgumentException("Invalid petId")
        }
        if (ownerId < 0) {
            throw IllegalArgumentException("Invalid ownerId")
        }
        if (companyId < 0) {
            throw IllegalArgumentException("Invalid companyId")
        }
        return petDao.adoptPet(petId, ownerId, companyId)
    }


    fun adoptMultiplePets(listPetId: List<Long>, ownerId: Long, companyId: Long): Int {
        validateInputs(listPetId, companyId)
        if (ownerId < 0) {
            throw IllegalArgumentException("Invalid ownerId")
        }
        return petDao.adoptMultiplePets(listPetId, ownerId, companyId)
    }

    fun addMultiplePets(listPetData: List<Pet>, companyId: Long): Int {
        if (listPetData.isEmpty()) {
            throw IllegalArgumentException("Invalid list pet Id")
        }
        if (companyId < 0) {
            throw IllegalArgumentException("Invalid companyId")
        }
        return petDao.addMultiplePets(listPetData, companyId)
    }

    private fun isValidPetType(value: String): Boolean {
        return enumValues<PetType>().any { it.name.equals(value, ignoreCase = true) }
    }

    fun validateInputs(listPetId: List<Long>, companyId: Long) {
        if (listPetId.isEmpty()) {
            throw IllegalArgumentException("Invalid list pet Id")
        }
        if (companyId < 0) {
            throw IllegalArgumentException("Invalid companyId")
        }
    }
}