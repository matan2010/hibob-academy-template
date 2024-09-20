package com.hibob.academy.resource

//import com.hibob.academy.dao.Pet
//import com.hibob.academy.dao.PetInsert
import com.hibob.academy.dao.Pet
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
@Path("/api/pets/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PetResource(private val petService: PetService) {

    @POST
    @Path("company/{companyId}/pets/add")
    fun addMultiplePets(
        listOfPets: List<Pet>,
        @PathParam("companyId") companyId: Long
    ): Response {
        return Response.ok(petService.addMultiplePets(listOfPets, companyId)).build()
    }


    @POST
    @Path("company/{companyId}/owner/{ownerId}/pets/adopt")
    fun adoptMultiplePets(
        listOfPetsId: List<Long>,
        @PathParam("ownerId") ownerId: Long,
        @PathParam("companyId") companyId: Long
    ): Response {
        return Response.ok(petService.adoptMultiplePets(listOfPetsId, ownerId, companyId)).build()
    }

}