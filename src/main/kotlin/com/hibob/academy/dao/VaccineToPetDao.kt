package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import org.jooq.DSLContext

class VaccineToPetDao (private val sql: DSLContext){
}

class VaccineToPetTable(tableName: String) : JooqTable(tableName) {
    val id = createBigIntField("id")
    val vaccineId = createVarcharField("name")
    val petId= createVarcharField("pet_id")
    val vaccinationDate=createVarcharField("vaccination_date")

    companion object {
        val instance = VaccineTable("vaccine")
    }
}