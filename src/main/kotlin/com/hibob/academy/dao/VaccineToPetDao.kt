package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import org.jooq.DSLContext

class VaccineToPetDao (private val sql: DSLContext){
    private val table = VaccineToPetTable.instance
}

class VaccineToPetTable(tableName: String) : JooqTable(tableName) {
    val id = createBigIntField("id")
    val vaccineId = createBigIntField("vaccine_id")
    val petId= createBigIntField("pet_id")
    val vaccinationDate=createDateField("vaccination_date")

    companion object {
        val instance = VaccineToPetTable("vaccine_to_pet")
    }
}