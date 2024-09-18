package com.hibob.academy.dao

import org.jooq.DSLContext
import org.jooq.RecordMapper
import org.jooq.Record
import org.jooq.impl.DSL
import org.springframework.stereotype.Component
import java.time.LocalDate


@Component
class PetDao(private val sql: DSLContext) {

    private val petTable = PetTable.instance

    private val petMapper = RecordMapper<Record, PetData> { record ->
        PetData(
            record[petTable.id],
            record[petTable.name],
            PetType.fromDatabaseValue(record[petTable.type]),
            record[petTable.dateOfArrival],
            record[petTable.companyId],
            record[petTable.ownerId]

        )
    }

    fun getPetsByType(companyId: Long, type: PetType): List<PetData> {
        return sql.select(
            petTable.id,
            petTable.name,
            petTable.dateOfArrival,
            petTable.companyId,
            petTable.type,
            petTable.ownerId
        )
            .from(petTable)
            .where(petTable.type.eq(type.name.lowercase()))
            .and(petTable.companyId.eq(companyId))
            .fetch(petMapper)
    }


    fun insertPet(name: String, type: PetType, companyId: Long, ownerId: Long?) {
        sql.insertInto(petTable)
            .set(petTable.name, name)
            .set(petTable.ownerId, ownerId)
            .set(petTable.dateOfArrival, LocalDate.now())
            .set(petTable.companyId, companyId)
            .set(petTable.type, type.toDatabaseValue())
            .execute()
    }


    fun adoptPet(petId: Long, ownerId: Long, companyId: Long) {
        sql.update(petTable)
            .set(petTable.ownerId, ownerId)
            .where(petTable.id.eq(petId))
            .and(petTable.companyId.eq(companyId))
            .execute()
    }


    fun adoptMultiplePets(listPetId: List<Long>, ownerId: Long, companyId: Long): Int {
        return sql.update(petTable)
            .set(petTable.ownerId, ownerId)
            .where(petTable.id.`in`(listPetId))
            .and(petTable.companyId.eq(companyId))
            .execute()
    }

    fun addMultiplePets(listPetData: List<Pet>, companyId: Long): Int {
        val insert = sql.insertInto(petTable)
            .columns(petTable.name, petTable.type, petTable.companyId, petTable.dateOfArrival, petTable.ownerId)
            .values(DSL.param(petTable.name), DSL.param(petTable.type), DSL.param(petTable.companyId), DSL.param(petTable.dateOfArrival), DSL.param(petTable.ownerId))

        val batch = sql.batch(insert)

        listPetData.forEach { pet ->
            batch.bind(
                pet.name,
                pet.type.name,
                companyId,
                LocalDate.now(),
                pet.ownerId
            )
        }

        return batch.execute().sum()
    }


}
