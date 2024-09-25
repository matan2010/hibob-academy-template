package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackDao
import com.hibob.academy.employeeFeedback.dao.FeedbackStatus
import com.hibob.academy.employeeFeedback.dao.Role
import jakarta.ws.rs.BadRequestException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.time.LocalDate

class FeedbackServiceTest {
    private val feedbackDaoMock = mock<FeedbackDao> {}
    private val feedbackService = FeedbackService(feedbackDaoMock)
    private val companyId: Long = 8L


    @Test
    fun `viewAllFeedback should be successful`() {
        whenever(feedbackDaoMock.viewAllFeedback(companyId)).thenReturn(any())
        feedbackService.viewAllFeedback(companyId)
        verify(feedbackDaoMock).viewAllFeedback(companyId)
    }

    @Test
    fun `insertFeedback should be successful`() {
        val feedback = Feedback("The feedback is ok", null, companyId, LocalDate.now(), FeedbackStatus.UNREVIEWED)
        whenever(feedbackDaoMock.insertFeedback(feedback)).thenReturn(true)
        feedbackService.insertFeedback(feedback)
        verify(feedbackDaoMock).insertFeedback(feedback)
    }


}