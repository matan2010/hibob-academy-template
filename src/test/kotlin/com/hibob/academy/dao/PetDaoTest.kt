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
        val dateOfArrival = LocalDate.of(2023, 5, 20)
        val companyId = 123L

        val petTest = PetData(name, dateOfArrival, companyId)
        dao.createNewPet(petTest)
        assertEquals(1, dao.getPets(PetType.Dog))
    }


}