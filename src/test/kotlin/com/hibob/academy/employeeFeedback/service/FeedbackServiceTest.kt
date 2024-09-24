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
    fun `viewAllFeedback should throw exception if the role is employee`() {
        val exception = assertThrows<BadRequestException> {
            feedbackService.viewAllFeedback(Role.EMPLOYEE, companyId)
        }
        assertEquals("You do not have permission to see all the feedback.", exception.message)
        verify(feedbackDaoMock, never()).viewAllFeedback(companyId)
    }

    @Test
    fun `viewAllFeedback should be successful`() {
        whenever(feedbackDaoMock.viewAllFeedback(companyId)).thenReturn(any())
        feedbackService.viewAllFeedback(Role.HR, companyId)
        verify(feedbackDaoMock).viewAllFeedback(companyId)
    }

    @Test
    fun `insertFeedback should throw exception if the feedback is too short`() {
        val feedback = Feedback("Hi", null, companyId, LocalDate.now(), FeedbackStatus.UNREVIEWED)
        val exception = assertThrows<BadRequestException> {
            feedbackService.insertFeedback(feedback)
        }
        assertEquals("The feedback is too short.", exception.message)
        verify(feedbackDaoMock, never()).insertFeedback(feedback)
    }

    @Test
    fun `insertFeedback should be successful`() {
        val feedback = Feedback("The feedback is ok", null, companyId, LocalDate.now(), FeedbackStatus.UNREVIEWED)
        whenever(feedbackDaoMock.insertFeedback(feedback)).thenReturn(true)
        feedbackService.insertFeedback(feedback)
        verify(feedbackDaoMock).insertFeedback(feedback)
    }


}