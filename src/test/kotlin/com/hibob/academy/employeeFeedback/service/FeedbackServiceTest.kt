package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.Feedback
import com.hibob.academy.employeeFeedback.dao.FeedbackDao
import com.hibob.academy.employeeFeedback.dao.FeedbackQueryParams
import com.hibob.academy.employeeFeedback.dao.FeedbackStatus
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import kotlin.random.Random

class FeedbackServiceTest {
    private val feedbackDaoMock = mock<FeedbackDao> {}
    private val feedbackService = FeedbackService(feedbackDaoMock)
    private val companyId: Long = Random.nextLong()


    @Test
    fun `viewAllFeedback should be successful`() {
        whenever(feedbackDaoMock.viewAllFeedback(companyId)).thenReturn(emptyList())
        feedbackService.viewAllFeedback(companyId)
        verify(feedbackDaoMock).viewAllFeedback(companyId)
    }

    @Test
    fun `insertFeedback should be successful`() {
        val feedback = Feedback("The feedback is ok", null)
        whenever(feedbackDaoMock.insertFeedback(feedback, companyId)).thenReturn(true)
        feedbackService.insertFeedback(feedback, companyId)
        verify(feedbackDaoMock).insertFeedback(feedback, companyId)
    }

    @Test
    fun `updateFeedbackStatus should be successful`() {
        whenever(feedbackDaoMock.updateFeedbackStatus(3L, companyId, FeedbackStatus.UNREVIEWED)).thenReturn(true)
        feedbackService.updateFeedbackStatus(3L, companyId, FeedbackStatus.REVIEWED)
        verify(feedbackDaoMock).updateFeedbackStatus(3L, companyId, FeedbackStatus.REVIEWED)
    }

    @Test
    fun `checkFeedbackStatus should be successful`() {
        whenever(feedbackDaoMock.checkFeedbackStatus(3L, companyId, companyId)).thenReturn(String.toString())
        feedbackService.checkFeedbackStatus(3L, companyId, companyId)
        verify(feedbackDaoMock).checkFeedbackStatus(3L, companyId, companyId)
    }


    @Test
    fun `getFeedbackByParams should be successful`() {
        val feedbackQueryParams = FeedbackQueryParams(null, null, false)
        whenever(feedbackDaoMock.getFeedbackByParams(eq(companyId), any())).thenReturn(emptyList())
        feedbackService.getFeedbackByParams(companyId, feedbackQueryParams)
        verify(feedbackDaoMock).getFeedbackByParams(eq(companyId), any())
    }



}