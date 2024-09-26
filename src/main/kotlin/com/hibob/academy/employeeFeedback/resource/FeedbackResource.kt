package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.EmployeeData
import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.Role
import com.hibob.academy.employeeFeedback.service.FeedbackService
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
class FeedbackResource(private val feedbackService: FeedbackService) {

    @Path("/feedback/view")
    @GET
    fun viewAllFeedback(@Context requestContext: ContainerRequestContext): Response {
        val employeeData = requestContext.getProperty(AuthenticationFilter.EMPLOYEE) as EmployeeData?
            ?: return Response.status(Response.Status.UNAUTHORIZED).build()
        val role = employeeData.role
        if (role == Role.EMPLOYEE) {
            throw NotAuthorizedException("You do not have permission to see all the feedback.")
        }
        val companyId = employeeData.companyId
        return Response.ok(feedbackService.viewAllFeedback(companyId)).build()
    }

    @Path("/feedback")
    @POST
    fun insertFeedback(feedback: Feedback,@Context requestContext: ContainerRequestContext): Response {
        if (feedback.feedback.length < 10) {
            throw BadRequestException("The feedback is too short.")
        }
        val employeeData = requestContext.getProperty(AuthenticationFilter.EMPLOYEE) as EmployeeData?
            ?: return Response.status(Response.Status.UNAUTHORIZED).build()
        val companyId = employeeData.companyId
        return Response.ok(feedbackService.insertFeedback(feedback,companyId)).build()
    }


    @Path("/updateFeedbackStatus")
    @POST
    fun updateFeedbackStatus(
        feedbackId: Long,
        @Context requestContext: ContainerRequestContext
    ): Response {
//        val result = feedbackService.updateFeedbackStatus(feedbackId, companyId, feedbackStatus)
//        return if (result) {
//            ResponseEntity.ok(true)
//        } else {
//            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false)
//        }
    }


    @Path("/checkFeedbackStatus")
    @GET
    fun checkFeedbackStatus(
//        @RequestParam feedbackId: Long,
//        @RequestParam employeeId: Long,
//        @RequestParam companyId: Long
//    ): Response{
//        val status = feedbackService.checkFeedbackStatus(feedbackId, employeeId, companyId)
//        return if (status != null) {
//            ResponseEntity.ok(status)
//        } else {
//            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
//        }
    }


    @Path("/feedbackByParams")
    @GET
    fun getFeedbackByParams(
//        @RequestParam companyId: Long,
//        @RequestParam(required = false) date: LocalDate?,
//        @RequestParam(required = false) department: Department?,
//        @RequestParam nullEmployeeId: Boolean
//    ): Response {
//        val params = FeedbackQueryParams(date, department, nullEmployeeId)
//        val feedbackList = feedbackService.getFeedbackByParams(companyId, params)
//        return ResponseEntity.ok(feedbackList)
    }


}

