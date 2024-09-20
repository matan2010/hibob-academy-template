package com.hibob.academy.resource




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
class PetResource (private val petService: PetService) {

    @GET
    @Path("ownerId/{ownerId}/company/{companyId}/pets")
    fun getPetsByOwnerId( @PathParam("ownerId") ownerId: Long,@PathParam("companyId") companyId: Long): Response {
        return Response.ok(petService.getPetsByOwnerId(ownerId,companyId)).build()
    }

    @GET
    @Path("company/{companyId}/pets/count")
    fun countPetsByType( @PathParam("companyId") companyId: Long): Response {
        return Response.ok(petService.countPetsByType(companyId)).build()
    }
}