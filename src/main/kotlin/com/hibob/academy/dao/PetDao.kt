package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper

class PetDao(private val sql: DSLContext) {

    private val table = PetTable.instance

    private val patMapper = RecordMapper<Record, PetTable>
    { record ->
        PetTable(
            record[table.name],
            record[table.type],
            record[table.companyId],
            record[table.dateOfArrival]
        )
    }

//    fun getCars(): List<PetTable> {
//        sql.select(c.licensePlate, c.manufacturer)
//            .from(c)
//            .where(c.companyId.eq(companyId))
//            .orderBy(c.manufacturer)
//            .fetch (carMapper)
//    }

}