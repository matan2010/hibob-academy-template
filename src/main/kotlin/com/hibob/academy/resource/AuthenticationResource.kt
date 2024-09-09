package com.hibob.academy.resource
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import com.hibob.academy.service.SessionService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.springframework.stereotype.Controller
//
//import com.hibob.academy.service.ExampleService
//import com.hibob.academy.service.SessionService
//import jakarta.ws.rs.Consumes
//import jakarta.ws.rs.POST
//import jakarta.ws.rs.Path
//import jakarta.ws.rs.Produces
//import jakarta.ws.rs.core.Response
//import org.springframework.http.MediaType
//import org.springframework.http.ResponseEntity
//import org.springframework.stereotype.Component
//import org.springframework.stereotype.Controller
//import java.awt.PageAttributes
//import java.util.*
import org.springframework.web.bind.annotation.RequestBody



@Controller
@Path("/jwt")
class AuthenticationResource(private val sessionService: SessionService) {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addNewUser(@RequestBody user: User): String {
        println("sdjfsdhjfgdhsfg")
        return sessionService.creatJwrToken(user)
    }
    data class User(val email: String, val name: String,val isAdmin: Boolean)
}