package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper



class OwnerDao (private val sql: DSLContext) {
    private val ownerTable = OwnerTable.instance

    private val ownerMapper = RecordMapper<Record, OwnerData>  { record ->
        OwnerData(
            record[ownerTable.id],
            record[ownerTable.name],          // Use getValue for accessing field values
            record[ownerTable.employeeId],    // Use getValue for accessing field values
            record[ownerTable.companyId]     // Use getValue for accessing field values
        )
    }

    fun getAllOwner(companyId:Long): List<OwnerData> {
        return sql.select(ownerTable.id,ownerTable.name,ownerTable.employeeId,ownerTable.companyId)
            .from(ownerTable)
            .where(ownerTable.companyId.eq(companyId))
            .fetch(ownerMapper)
    }

    fun createNewOwner(name:String,employeeId:Long,companyId: Long){
        sql.insertInto(ownerTable)
            .set(ownerTable.name ,name)
            .set(ownerTable.companyId ,companyId)
            .set(ownerTable.employeeId ,employeeId)
            .onConflict(ownerTable.companyId,ownerTable.employeeId)
            .doNothing()
            .execute()
    }

}