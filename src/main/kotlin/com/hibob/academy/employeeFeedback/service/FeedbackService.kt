package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackDao
import com.hibob.academy.employeeFeedback.dao.FeedbackData
import com.hibob.academy.employeeFeedback.dao.Role
import jakarta.ws.rs.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FeedbackService @Autowired constructor(private val feedbackDao: FeedbackDao) {

    fun viewAllFeedback(role: Role, companyId: Long): List<FeedbackData> {
        if (role == Role.EMPLOYEE) {
            throw BadRequestException("You do not have permission to see all the feedback.")
        }
        return feedbackDao.viewAllFeedback(companyId)
    }

    fun insertFeedback(feedback: Feedback) {
        if (feedback.feedback.length < 10) {
            throw BadRequestException("The feedback is too short.")
        }
        feedbackDao.insertFeedback(feedback)
    }
}