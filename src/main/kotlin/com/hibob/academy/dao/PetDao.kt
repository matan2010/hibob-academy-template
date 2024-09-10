package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record
import java.sql.Date // If you still need it
import java.time.LocalDate
class PetDao(private val sql: DSLContext) {

    private val petTable = PetTable.instance

    private val patMapper: RecordMapper<Record, PetData> = RecordMapper { record ->
        PetData(
            record.getValue(petTable.name),          // Use getValue for accessing field values
            record.getValue(petTable.type),          // Use getValue for accessing field values
            record.getValue(petTable.companyId).toInt(),     // Use getValue for accessing field values
            record.getValue(petTable.dateOfArrival)?.toLocalDate()// Converts Date to LocalDate
        )
    }

//    fun getPets(): List<PetTable> {
//        return sql.select()
//            .from(petTable)
//            .fetch (patMapper)
//    }

}