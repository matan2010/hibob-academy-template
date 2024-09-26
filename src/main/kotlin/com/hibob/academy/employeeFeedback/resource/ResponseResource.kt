package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.EmployeeData
import com.hibob.academy.employeeFeedback.dao.Feedback
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
class ResponseResource {//(private val responseService: ResponseService) {


//    @Path("/response")
//    @POST
//    fun insertResponse(feedback: Feedback, @Context requestContext: ContainerRequestContext): Response {
//        if (feedback.feedback.length < 10) {
//            throw BadRequestException("The feedback is too short.")
//        }
//        val employeeData = requestContext.getProperty(AuthenticationFilter.EMPLOYEE) as EmployeeData?
//            ?: return Response.status(Response.Status.UNAUTHORIZED).build()
//        val companyId = employeeData.companyId
//        return Response.ok(feedbackService.insertFeedback(feedback,companyId)).build()
//    }
//fun insertResponse(feedbackResponse: FeedbackResponse, employeeId: Long): Boolean {
//        ResponseDao(feedbackResponse,employeeId)
//    }
}