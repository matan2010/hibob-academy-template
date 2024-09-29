package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.*
import com.hibob.academy.employeeFeedback.service.FeedbackService
import com.hibob.academy.filters.AuthenticationFilter
import jakarta.ws.rs.*
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/employee/feedback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FeedbackResource(private val feedbackService: FeedbackService) : BaseResource() {

    @Path("/view")
    @GET
    fun viewAllFeedback(
        @Context requestContext: ContainerRequestContext
    ):
            Response {
        val employeeData = getEmployeeData(requestContext)
        checkHROrManager(employeeData)
        val companyId = employeeData.companyId
        return Response.ok(feedbackService.viewAllFeedback(companyId)).build()
    }

    @POST
    fun insertFeedback(
        feedback: Feedback,
        @Context requestContext: ContainerRequestContext
    ):
            Response {
        validateFeedback(feedback)
        val employeeData = getEmployeeData(requestContext)
        val companyId = employeeData.companyId
        return Response.ok(feedbackService.insertFeedback(feedback, companyId)).build()
    }

    @Path("/status")
    @POST
    fun updateFeedbackStatus(
        feedbackInfo: FeedbackInfo,
        @Context requestContext: ContainerRequestContext
    ): Response {
        val employeeData = getEmployeeData(requestContext)
        checkHR(employeeData)
        val companyId = employeeData.companyId
        val feedbackId = feedbackInfo.feedbackId
        val feedbackStatus = FeedbackStatus.fromDatabaseValue(feedbackInfo.feedbackStatus)
        return Response.ok(
            feedbackService.updateFeedbackStatus(
                feedbackId,
                companyId,
                feedbackStatus
            )
        ).build()
    }

    @Path("/status/{feedbackId}")
    @GET
    fun checkFeedbackStatus(
        @PathParam("feedbackId") feedbackId: Long,
        @Context requestContext: ContainerRequestContext
    ): Response {
        val employeeData = getEmployeeData(requestContext)
        val companyId = employeeData.companyId
        val employeeId = employeeData.id
        return Response.ok(feedbackService.checkFeedbackStatus(feedbackId, employeeId, companyId)).build()
    }


    @Path("/search")
    @POST
    fun getFeedbackByParams(
        feedbackQueryParams: FeedbackQueryParams,
        @Context requestContext: ContainerRequestContext
    ): Response {
        val employeeData = getEmployeeData(requestContext)
        checkHROrManager(employeeData)
        val companyId = employeeData.companyId
        val feedbackData = feedbackService.getFeedbackByParams(companyId, feedbackQueryParams)
        return Response.ok(feedbackData).build()
    }

    private fun validateFeedback(feedback: Feedback) {
        if (feedback.feedback.length < 10) {
            throw BadRequestException("The feedback is too short.")
        }
    }

}

