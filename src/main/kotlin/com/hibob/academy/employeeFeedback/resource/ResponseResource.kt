package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.EmployeeData
import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackResponse
import com.hibob.academy.employeeFeedback.dao.Role
import com.hibob.academy.employeeFeedback.service.ResponseService
import com.hibob.academy.filters.AuthenticationFilter
import jakarta.ws.rs.*
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ResponseResource (private val responseService: ResponseService) {


    @Path("/response")
    @POST
    fun insertResponse(feedbackResponse: FeedbackResponse, @Context requestContext: ContainerRequestContext): Response {
        val employeeData = requestContext.getProperty(AuthenticationFilter.EMPLOYEE) as EmployeeData?
            ?: return Response.status(Response.Status.UNAUTHORIZED).build()
        val employeeId = employeeData.id
        val role = employeeData.role
        if (role != Role.HR) {
            throw NotAuthorizedException("You do not have permission to insert Response.")
        }
        return Response.ok(responseService.insertResponse(feedbackResponse,employeeId)).build()
    }
}