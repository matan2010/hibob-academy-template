package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record
import java.time.LocalDate

class PetDao(private val sql: DSLContext) {

    private val petTable = PetTable.instance
    private val ownerTable = OwnerTable.instance

    private val patMapper = RecordMapper<Record, PetData>  { record ->
        PetData(
            record[petTable.id],
            record[petTable.name],
            PetType.valueOf(record[petTable.type].uppercase()),
            record[petTable.dateOfArrival],
            record[petTable.companyId],
            record[petTable.ownerId]

        )
    }

    fun getPetsByType(companyId:Long,type: PetType): List<PetData> {
        return sql.select(petTable.id,petTable.name, petTable.dateOfArrival, petTable.companyId, petTable.type, petTable.ownerId)
            .from(petTable)
            .where(petTable.type.eq(type.name.lowercase()))
            .and(petTable.companyId.eq(companyId))
            .fetch(patMapper)
    }


    fun insertPet(name: String, type: PetType,companyId: Long ,ownerId:Long?) {
        sql.insertInto(petTable)
            .set(petTable.name ,name)
            .set(petTable.ownerId, ownerId)
            .set(petTable.dateOfArrival , LocalDate.now())
            .set(petTable.companyId ,companyId)
            .set(petTable.type ,type.toDatabaseValue())
            .onConflict(petTable.companyId,petTable.ownerId)
            .doNothing()
            .execute()
    }

    fun adoptPet(petId: Long, ownerId: Long){
        sql.update(petTable)
            .set(petTable.ownerId, ownerId)
            .where(petTable.id.eq(petId))
            .execute()
    }


//    fun getOwnerByPetId(petId: Long,companyId: Long): OwnerData? {
//        return sql.select(ownerTable.name, ownerTable.employeeId,ownerTable.companyId)//,ownerTable.id)
//            .from(ownerTable)
//            .join(petTable).on(petTable.ownerId.eq(ownerTable.employeeId))
//            .where(petTable.id.eq(petId),petTable.companyId.eq(companyId))
//            .fetchOneInto(OwnerData::class.java)
//    }

    fun getOwnerByPetId(petId: Long, companyId: Long): OwnerData? {
        return sql.select(
            ownerTable.id,
            ownerTable.name,
            ownerTable.employeeId,
            ownerTable.companyId
        )
            .from(ownerTable)
            .join(petTable).on(petTable.ownerId.eq(ownerTable.id)) // Assuming ownerId from pets table relates to id from owner table
            .where(
                petTable.id.eq(petId),
                petTable.companyId.eq(companyId)
            )
            .fetchOneInto(OwnerData::class.java)
    }


}
