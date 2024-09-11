package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper

class OwnerDao (private val sql: DSLContext) {
    private val ownerTable = OwnerTable.instance

    private val ownerMapper = RecordMapper<Record, OwnerData>  { record ->
        OwnerData(
            record[ownerTable.name],          // Use getValue for accessing field values
            record[ownerTable.employeeId],    // Use getValue for accessing field values
            record[ownerTable.companyId]     // Use getValue for accessing field values
        )
    }

    fun getAllOwner(): List<OwnerData> {
        return sql.select(ownerTable.name,ownerTable.employeeId,ownerTable.companyId)
            .from(ownerTable)
            .fetch(ownerMapper)
    }

    fun createNewOwner(owner:OwnerData){
        sql.insertInto(ownerTable)
            .set(ownerTable.name ,owner.name)
            .set(ownerTable.companyId ,owner.companyId)
            .set(ownerTable.employeeId ,owner.employeeId)
            .onConflict(ownerTable.companyId,ownerTable.employeeId)
            .doNothing()
            .execute()
    }

}