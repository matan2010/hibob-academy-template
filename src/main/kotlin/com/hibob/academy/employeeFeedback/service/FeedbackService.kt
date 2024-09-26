package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackDao
import com.hibob.academy.employeeFeedback.dao.FeedbackData
import com.hibob.academy.employeeFeedback.dao.FeedbackStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FeedbackService @Autowired constructor(private val feedbackDao: FeedbackDao) {

    fun viewAllFeedback(companyId: Long): List<FeedbackData> {
        return feedbackDao.viewAllFeedback(companyId)
    }

    fun insertFeedback(feedback: Feedback, companyId: Long) {
        feedbackDao.insertFeedback(feedback, companyId)
    }

    fun updateFeedbackStatus(feedbackId: Long, companyId: Long, feedbackStatus: FeedbackStatus): Boolean {
        //retrun FeedbackDao.updateFeedbackStatus(feedbackId,companyId, feedbackStatus)
        TODO()
    }

    fun checkFeedbackStatus(feedbackId: Long, employeeId: Long, companyId: Long): String? {
        //retrun FeedbackDao.checkFeedbackStatus(feedbackId,employeeId, companyId)
        TODO()
    }
    //getFeedbackByParams

    //fun getFeedbackByParams(companyId: Long,params:FeedbackQueryParams): List<FeedbackData>{
        //retrun FeedbackDao.getFeedbackByParams(feedbackId,params)
      //  TODO()
    //}


}