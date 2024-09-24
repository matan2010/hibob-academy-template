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
        private const val LOGIN_PATH = "/api/employee/login"
        private const val COOKIE_NAME = "matan_name"  // Replace with actual cookie name
    }

    override fun filter(requestContext: ContainerRequestContext) {

        if (requestContext.uriInfo.path == LOGIN_PATH) return

        val jwtCookie = requestContext.cookies[COOKIE_NAME]?.value
        verify(jwtCookie, requestContext)
    }

    fun verify(cookie: String?, requestContext: ContainerRequestContext) =
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(cookie)
        } catch (e: Exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build())
        }

}