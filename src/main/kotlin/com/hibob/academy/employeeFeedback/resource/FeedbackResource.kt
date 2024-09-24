package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.service.FeedbackService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.springframework.stereotype.Controller

@Controller
@Path("/api/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FeedbackResource (private val feedbackService: FeedbackService) {
}

