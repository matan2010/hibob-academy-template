package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.service.FeedbackService
import com.hibob.academy.employeeFeedback.service.SessionEmployeeService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.HttpServletRequest
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FeedbackResource(private val feedbackService: FeedbackService,
    @Context private val request: HttpServletRequest) {

    @Path("/feedback")
    @GET
    fun viewAllFeedback(): Response {
        val cookies = request.cookies
        val jwtCookie = cookies.find { it.name == "matan_name" }

        if (jwtCookie == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("JWT cookie 'matan_name' is missing.")
                .build()
        }

        val claims: Claims = try {
            Jwts.parser()
                .setSigningKey(SessionEmployeeService.SECRET_KEY) // Ensure you use the correct key
                .parseClaimsJws(jwtCookie.value)
                .body
        } catch (e: Exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid JWT token.").build()
        }
        val companyId =  claims["companyId"]?.let { it as? Number }?.toLong()
        val role = claims["role"]?.toString()
        val department = claims["department"]?.toString()
        //return Response.ok(feedbackService.viewAllFeedback(companyId)).build()
        return Response.ok().build()
    }

    @Path("/feedback")
    @POST
    fun insertFeedback(feedback: Feedback): Response {
        return Response.ok(feedbackService.insertFeedback(feedback)).build()
    }

}

