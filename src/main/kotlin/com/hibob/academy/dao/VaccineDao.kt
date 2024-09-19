package com.hibob.academy.dao

import com.hibob.academy.utils.JooqTable
import org.jooq.DSLContext

class VaccineDao (private val sql: DSLContext){
    private val table = VaccineTable.instance
}

class VaccineTable(tableName: String) : JooqTable(tableName) {
    val id = createBigIntField("id")
    val name = createVarcharField("name")

    companion object {
        val instance = VaccineTable("vaccine")
    }
}