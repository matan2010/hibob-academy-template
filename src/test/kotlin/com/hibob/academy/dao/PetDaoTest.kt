package com.hibob.academy.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@BobDbTest
class PetDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val companyId = 8L
    private val table = PetTable.instance
    private val dao = PetDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).execute()
    }

    @Test
    fun `make a new pet`() {
        val name = "Buddy"
        dao.insertPet(name, PetType.DOG, companyId, null)
        val petsList = dao.getPetsByType(companyId, PetType.DOG)

        assertEquals(1, petsList.size)
        assertEquals(companyId, petsList[0].companyId)
        assertEquals("Buddy", petsList[0].name)
        assertEquals(PetType.DOG, petsList[0].type)
    }


    @Test
    fun `pet by type when in the db not exists pets with this type`() {
        val name = "Buddy"
        dao.insertPet(name, PetType.DOG, companyId, null)
        val petsList = dao.getPetsByType(companyId, PetType.CAT)
        assertEquals(emptyList<PetData>(), petsList)

    }


    @Test
    fun `adopt a pet`() {
        val name = "Buddy"
        dao.insertPet(name, PetType.DOG, companyId, null)
        val petsList = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(null, petsList[0].ownerId)

        dao.adoptPet(petsList[0].id, 1L, companyId)
        val petsListAfterUpdate = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(1L, petsListAfterUpdate[0].ownerId)
    }

    @Test
    fun `adopt a pet which is already adopted`() {
        val name = "Buddy"
        dao.insertPet(name, PetType.DOG, companyId, null)
        val pet = dao.getPetsByType(companyId, PetType.DOG)[0]
        dao.adoptPet(pet.id, 1L, companyId)
        dao.adoptPet(pet.id, 2L, companyId)
        val petsListAfterAdoption = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(2L, petsListAfterAdoption[0].ownerId)
    }


    @Test
    fun `adopt Multiple Pets whit owner Id`() {

        dao.insertPet("Buddy", PetType.DOG, companyId, null)
        dao.insertPet("Bob", PetType.CAT, 7L, null)
        dao.insertPet("Rookie", PetType.DOG, companyId, 4L)

        val listPet = dao.getPetsByType(companyId, PetType.DOG)

        val listPetsId = listPet.map { it.id }
        val countPets = dao.adoptMultiplePets(listPetsId, 7L, companyId)
        assertEquals(countPets, listPetsId.size)
        val listPetAdoption = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(7L, listPetAdoption[0].ownerId)
        assertEquals(7L, listPetAdoption[1].ownerId)
    }

    @Test
    fun `add multiple pets with batch insert`() {
        val pet1 = Pet("Buddy", PetType.DOG, companyId, null)
        val pet2 = Pet("Bob", PetType.CAT, companyId, null)
        val pet3 = Pet("Rookie", PetType.DOG, companyId, 4L)

        val listPets = listOf<Pet>(pet1, pet2, pet3)
        val countPets = dao.addMultiplePets(listPets, companyId)
        assertEquals(countPets, listPets.size)
    }
}