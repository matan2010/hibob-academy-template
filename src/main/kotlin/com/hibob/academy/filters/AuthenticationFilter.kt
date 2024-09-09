package com.hibob.academy.filters
////
////import com.hibob.academy.service.SessionService.Companion.SECRET_KEY
////import io.jsonwebtoken.Claims
////import io.jsonwebtoken.Jws
////import io.jsonwebtoken.Jwts
////import jakarta.ws.rs.container.ContainerRequestContext
////import jakarta.ws.rs.container.ContainerRequestFilter
////import jakarta.ws.rs.core.Response
////import jakarta.ws.rs.ext.Provider
////import org.apache.tomcat.util.http.parser.Cookie
////import org.springframework.stereotype.Component
//import com.hibob.academy.service.SessionService.Companion.SECRET_KEY
//import io.jsonwebtoken.Claims
//import io.jsonwebtoken.Jws
//import io.jsonwebtoken.Jwts
//import jakarta.ws.rs.container.ContainerRequestContext
//import jakarta.ws.rs.container.ContainerRequestFilter
//import jakarta.ws.rs.core.Response
//import jakarta.ws.rs.ext.Provider
//import org.springframework.stereotype.Component
//
//@Component
//@Provider
//class AuthenticationFilter: ContainerRequestFilter {
//
//    override fun filter(requestContext: ContainerRequestContext) {
//
//        if(requestContext.uriInfo.path == "jwt/login") return
//
//        val cookies = requestContext.cookies
//        val jwtCookie = cookies["matan_cookie"]?.value
//
//        val claims = verify(jwtCookie)
//
//        if(claims == null) {
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
//        }
//    }
//
//    fun verify(cookie: String?): Jws<Claims>? =
//        cookie?.let {
//            try{
//                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(it)
//            } catch (e: Exception) {
//                null
//            }
//        }
////    override fun filter(requestContext: ContainerRequestContext) {
////        //println("jshdghjskcgvjkhcsvgcxjkhvgcjhxvgjhcxgvjhvcx")
////        if(requestContext.uriInfo.path=="jwt/login") return
////
////        val cookie = requestContext.cookies["matan_cookie"]?.value
////        val jwtClaims = verify(cookie)
////
////        if(jwtClaims == null) {
////            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
////        }
////        //print("SUCESS")
////        // verify token is valid
////    }
////
////    private val jwtParser = Jwts.parser().setSigningKey(SECRET_KEY)
////
////    fun verify(cookie: String?): Jws<Claims>? =
////        cookie?.let {
////            try {
////                jwtParser.parseClaimsJws(it)
////            } catch (ex: Exception) {
////                null
////            }
////        }
//}

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import org.springframework.stereotype.Component
import com.hibob.academy.service.SessionService.Companion.SECRET_KEY
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider


@Component
@Provider
class AuthenticationFilter : ContainerRequestFilter {
    override fun filter(requestContext: ContainerRequestContext) {
        if (requestContext.uriInfo.path == "jwt/users/login") return

        // Verify the JWT token from the cookie
        val cookie = requestContext.cookies
        val jwtClaims = verify(cookie["matan_cookie_name"]?.value)

        if (jwtClaims == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid or expired token").build())
        }
    }
    private val jwtParser = Jwts.parser().setSigningKey(SECRET_KEY)

    fun verify(cookie: String?): Jws<Claims>? =
        cookie?.let {
            try {
                jwtParser.parseClaimsJws(it)
            } catch (ex: Exception) {
                null
            }
        }
}