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
class PetDaoTest @Autowired constructor(private val sql: DSLContext)  {
    private val companyId:Long=8
    private val ownerId:Long=8
    private val table=PetTable.instance
    private val dao = PetDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).where(table.companyId.eq(companyId)).execute()
    }

    @Test
    fun `make a new pet`() {
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val petsList = dao.getPetsByType(companyId,PetType.DOG)

        assertEquals(1, petsList.size)
        assertEquals(companyId, petsList[0].companyId)
        assertEquals("Buddy", petsList[0].name)
        assertEquals(PetType.DOG, petsList[0].type)
    }


    @Test
    fun `pet by type when in the db not exists pets with this type`() {
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val petsList = dao.getPetsByType(companyId, PetType.CAT)
        assertEquals(emptyList<PetData>(), petsList)

// Assert that the filtered list is not empty, meaning the pet exists
        assertTrue(filteredPets.isNotEmpty(), "Pet Waffle should have been added to the database")
    }


    @Test
    fun `adopt a pet`(){
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val petsList = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(null, petsList[0].ownerId)

        dao.adoptPet(petsList[0].id, 1L,companyId)
        val petsListAfterUpdate = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(1L, petsListAfterUpdate[0].ownerId)
    }

    @Test
    fun `adopt a pet which is already adopted`() {
        val name = "Buddy"
        dao.insertPet(name,PetType.DOG,companyId,null)
        val pet = dao.getPetsByType(companyId, PetType.DOG)[0]
        dao.adoptPet(pet.id, 1L,companyId)
        dao.adoptPet(pet.id, 2L,companyId)
        val petsListAfterAdoption = dao.getPetsByType(companyId, PetType.DOG)
        assertEquals(2L, petsListAfterAdoption[0].ownerId)
    }



}