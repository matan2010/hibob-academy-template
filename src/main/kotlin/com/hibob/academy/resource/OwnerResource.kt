package com.hibob.academy.resource

import com.hibob.academy.dao.OwnerDao
import com.hibob.academy.service.OwnerService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/matan/sabag/owner/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class OwnerResource(private val service: OwnerService) {

    @GET
    @Path("companyId/{companyId}")
    fun getAllOwner(@PathParam("companyId") companyId: Long): Response {
        

    }

    @POST
    @Path("createNewOwner")
    fun createNewOwner(newOwner: Owner): Response {

    }

    @GET
    @Path("getOwnerByPetId/{petId}/companyId/{companyId}")
    fun getOwnerByPetId(): Response {

    }


}