package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.FeedbackResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.hibob.academy.employeeFeedback.dao.ResponseDao

@Component
class ResponseService @Autowired constructor(private val responseDao :ResponseDao){

    fun insertResponse(feedbackResponse: FeedbackResponse, employeeId: Long): Boolean {
        return responseDao.insertResponse(feedbackResponse,employeeId)
    }
}