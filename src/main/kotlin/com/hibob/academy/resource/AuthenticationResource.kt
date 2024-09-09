package com.hibob.academy.resource
////import jakarta.ws.rs.POST
////import jakarta.ws.rs.Path
////import jakarta.ws.rs.core.Response
////import com.hibob.academy.service.SessionService
////import jakarta.ws.rs.Consumes
////import jakarta.ws.rs.GET
////import jakarta.ws.rs.Produces
////import jakarta.ws.rs.core.MediaType
////import jakarta.ws.rs.core.NewCookie
////import org.springframework.stereotype.Controller
//import jakarta.ws.rs.POST
//import jakarta.ws.rs.Path
//import jakarta.ws.rs.core.Response
//import com.hibob.academy.service.SessionService
//import jakarta.ws.rs.Consumes
//import jakarta.ws.rs.GET
//import jakarta.ws.rs.Produces
//import jakarta.ws.rs.core.MediaType
//import jakarta.ws.rs.core.NewCookie
//import org.springframework.stereotype.Controller
//
//@Controller
//@Produces(MediaType.APPLICATION_JSON)
//@Path("/jwt")
//class AuthenticationResource(private val sessionService: SessionService) {
//
//    @POST
//    @Path("/login")
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun addNewUser( user: User): Response {
//        //println("§111§1111111§11111")
//        val cookie = NewCookie.Builder("matan_cookie").value(sessionService.creatJwrToken(user)).build()
//        return Response.ok().cookie(cookie).build()
//    }
//
//
////    @GET
////    @Path("/test")
////    @Consumes(MediaType.APPLICATION_JSON)
////    fun test(): Response {
////        return Response.ok().entity("yes").build()
////        //return creatJwrToken
////    }
//    @Path("/getAllUsers")
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun getAllUsers(): Response {
//        return Response.ok().entity("Yessssss").build()
//    }
//
//    data class User(val email: String, val name: String,val isAdmin: Boolean)
//
//}
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response
import com.hibob.academy.service.SessionService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.NewCookie
import org.springframework.stereotype.Controller

data class User(val email: String,val name: String, val isAdmin: Boolean)

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Path("/jwt/users")
class AuthenticationResourse(private val service: SessionService) {
      //AuthenticationResource
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun addNewUser(newUser: User): Response {
        val tokenJwt = service.creatJwrToken(newUser)  // Assuming createJWTToken returns a JWT
        val cookie = NewCookie.Builder("matan_cookie_name").value(tokenJwt).build()//Creating new cookie
        return Response.ok().cookie(cookie).build()
    }

    @Path("/getAllUsers")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    fun getAllUsers(): Response {
        return Response.ok().entity("Yessssss").build()
    }
}