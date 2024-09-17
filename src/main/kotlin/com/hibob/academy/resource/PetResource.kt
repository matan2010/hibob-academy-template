package com.hibob.academy.resource

//import com.hibob.academy.dao.Pet
import com.hibob.academy.dao.PetType
import com.hibob.academy.service.PetService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/matan/sabag/pets/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PetResource (private val petService: PetService) {

    @GET
    @Path("getAllPetsByType/{petType}/companyId/{companyId}")
    fun getPetsByType(@PathParam("petType") petType: String, @PathParam("companyId") companyId: Long): Response {
        val petsList = petService.getPetsByType(companyId,petType)
        return if (petsList.isEmpty())
            Response.noContent().build()
        else
            Response.ok(petsList).build()
    }

    @POST
    @Path("insertPet")
    fun insertPet(newPet: Pets): Response {
        val insertPetSerialId = petService.insertPet(newPet)
        return if (insertPetSerialId < 0L) {
            Response.status(Response.Status.OK).entity("Pet already exists").build()
        } else {
            Response.status(Response.Status.CREATED).entity("Pet successfully inserted").build()
        }
    }

    @PUT
    @Path("adoptPet/{petId}/ownerId/{newOwnerId}/companyId/{companyId}")
    fun adoptPet(
        @PathParam("petId") petId: Long,
        @PathParam("newOwnerId") newOwnerId: Long,
        @PathParam("companyId") companyId: Long
    ): Response {
        return try {
            val resultMessage = petService.adoptPet(petId, newOwnerId, companyId)
            Response.ok(resultMessage).build()
        } catch (e: IllegalArgumentException) {
            Response.status(Response.Status.BAD_REQUEST).entity(e.message).build()
        }
    }


}