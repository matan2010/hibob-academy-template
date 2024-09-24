package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackData
import com.hibob.academy.employeeFeedback.service.FeedbackService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.springframework.stereotype.Controller

@Controller
@Path("/api/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FeedbackResource (private val feedbackService: FeedbackService) {

//    @Path("/feedback")
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun viewAllFeedback(companyId: Long): Response {
//        return feedbackService.viewAllFeedback(companyId)
//    }
//
//    @Path("/feedback")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    fun insertFeedback(feedback: Feedback): Response{
//        feedbackService.insertFeedback(feedback)
//    }
}

