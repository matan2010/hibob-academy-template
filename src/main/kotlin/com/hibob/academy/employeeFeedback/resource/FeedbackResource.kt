package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.service.FeedbackService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FeedbackResource(private val feedbackService: FeedbackService) {

    @Path("/feedback")
    @GET
    fun viewAllFeedback(@CookieParam("companyId") companyId: Long,
                        @CookieParam("role") role: String
    ): Response {
        return Response.ok(feedbackService.viewAllFeedback(companyId)).build()
    }

    @Path("/feedback")
    @POST
    fun insertFeedback(feedback: Feedback): Response {
        return Response.ok(feedbackService.insertFeedback(feedback)).build()
    }

}

