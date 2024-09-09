package com.hibob.rest_API

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller
import java.util.*

val pets = mutableListOf(
    Pet(1L, "Buddy", "Dog", "101", Date()),
    Pet(2L, "Whiskers", "Cat", "102", Date()),
    Pet(3L, "Polly", "Parrot", "103", Date())
)



@Controller
@Path("/api/matan/pets/envelopes")
@Produces(MediaType.APPLICATION_JSON)
class PetController {

    @GET
    @Path("/getPetType/{petId}")
    fun getPetType(@PathParam("petId") petId: Long): Response {
        val pet = pets.find { it.petId == petId }

        return when {
            pet == null -> {
                throw NotFoundException("Pet not found")  // Pet not found
            }
            petId == 123L -> {
                Response.status(Response.Status.UNAUTHORIZED).build()  // Unauthorized for petId 123
            }
            else -> {
                Response.ok(pet.type).build()
            }
        }
    }

    @GET
    @Path("/getAllPets")
    fun getAllPets(): Response {
        return Response.ok(pets).build()
    }

    @POST
    @Path("/addPet/{newPet}")
    fun addPet(newPet: Pet): Response {
        pets.add(newPet)
        return Response.status(Response.Status.CREATED).entity(newPet).build()
    }


    @PUT
    @Path("/updatePet/{petId}")
    fun updatePet(@PathParam("petId") petId: Long, updatedPet: Pet): Response {
        val index = pets.indexOfFirst { it.petId == petId }
        return if (index >= 0) {
            pets[index] = updatedPet.copy(petId = petId) // Keep the same pet ID
            Response.ok(updatedPet).build()
        } else {
            throw NotFoundException("Pet not found")
        }
    }


    @DELETE
    @Path("/deletePet/{petId}")
    fun deletePet(@PathParam("petId") petId: Long): Response {
        val pet = pets.find { it.petId == petId }
        return if (pet != null) {
            pets.remove(pet)
            Response.ok(pet.petId).build()
        } else {
            throw NotFoundException("Pet not found")
        }
    }

}
