package com.hibob.academy.filters

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
    companion object {
        private const val LOGIN_PATH = "jwt/users/login"
        private const val COOKIE_NAME = "matan_name"  // Replace with actual cookie name
    }
    override fun filter(requestContext: ContainerRequestContext) {
        if (requestContext.uriInfo.path == LOGIN_PATH) return
        try {
            val cookie = requestContext.cookies

            val jwtClaims = verify(cookie[COOKIE_NAME]?.value) ?: throw Exception("Invalid or expired token")

        } catch (e: Exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(e.message).build())
            return
        }
    }

    private val jwtParser = Jwts.parser().setSigningKey(SECRET_KEY)

    fun verify(cookie: String?): Jws<Claims>? =
        cookie?.let {
            try {
                jwtParser.parseClaimsJws(it)
            } catch (ex: Exception) {
                return null
            }
        }
}