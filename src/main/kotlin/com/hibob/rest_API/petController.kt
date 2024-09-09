package com.hibob.rest_API

import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/matan/pets/envelopes")
@Produces(MediaType.APPLICATION_JSON)
class PetController {
    @GET
    @Path("/{petId}/type")
    fun getPetType(@PathParam("petId") petId: Long): Response {
        if(petId.toInt() == 123){
            throw NotFoundException("no pet found")
        }
        Response.status(Response.Status.UNAUTHORIZED).build()
        return Response.ok().build()
    }


    @POST
    @Path("/{petId}/type")
    fun createPet(pet: Pet): Response {

        return Response.ok().build()
    }

    @PUT/*UPDATE*/
    @Consumes(MediaType.APPLICATION_JSON)
    fun updatePet(pet: Pet): Response {
        return Response.ok().build()
    }

}