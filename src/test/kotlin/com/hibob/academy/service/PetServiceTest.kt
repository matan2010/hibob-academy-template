package com.hibob.academy.service

import org.junit.jupiter.api.assertThrows
import com.hibob.academy.dao.PetDao
import com.hibob.academy.dao.PetData
import com.hibob.academy.dao.PetType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate

class PetServiceTest{
        private val petDaoMock = mock<PetDao>{}
        private val petService = PetService(petDaoMock)
        private val validCompanyId = 8L
        private val illegalCompanyId = -1L
        private val validOwnerId= 5L
        private val inValidOwnerId= -5L
        private val validName = "Matan"
        private val ownerId = 10L
        private val illegalOwnerId = -10L
        private val validPetType = "DOG"
        private val invalidPetType = "123"
        private val petTypeDog = PetType.DOG
        private  val validPetId= 7L
        private  val illegalPetId= -7L


        @Test
        fun `getPetsByType should return pets successfully`() {
            val expectedPets = listOf(
                PetData(1, "Buddy",  petTypeDog, LocalDate.now(),validCompanyId,ownerId),
                PetData( 2, "Max",  petTypeDog,LocalDate.now(),validCompanyId,ownerId)
            )

            whenever(petDaoMock.getPetsByType(validCompanyId, petTypeDog)).thenReturn(expectedPets)

            val actualPets = petService.getPetsByType(validCompanyId, validPetType)

            assertEquals(expectedPets, actualPets)
            verify(petDaoMock).getPetsByType(validCompanyId, petTypeDog)
        }


        @Test
        fun `insertPet should insert pet successfully`() {
            petService.insertPet(validName, validPetType, validCompanyId, validOwnerId)
            verify(petDaoMock).insertPet(validName, petTypeDog, validCompanyId, validOwnerId)
        }

        @Test
        fun `adoptPet should adopt pet successfully`() {
            petService.adoptPet(validPetId, validOwnerId, validCompanyId)

            verify(petDaoMock).adoptPet(validPetId, validOwnerId, validCompanyId)
        }


        @Test
        fun `countPetsByType should adopt pet successfully`() {
            petService.countPetsByType(validCompanyId)
            verify(petDaoMock).countPetsByType(validCompanyId)
        }

        @Test
        fun `getPetsByOwnerId should get pet successfully`() {
            petService.getPetsByOwnerId(ownerId,validCompanyId)
            verify(petDaoMock).getPetsByOwnerId(ownerId,validCompanyId)
        }

}