package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class FeedbackDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val companyId: Long = -8L
    private val feedbackTable = FeedbackTable.instance
    private val employeeTable = EmployeeTable.instance
    private val feedbackDao = FeedbackDao(sql)
    private val employeeDao = EmployeeDao(sql)
    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(feedbackTable).where(feedbackTable.companyId.eq(companyId)).execute()
        sql.deleteFrom(employeeTable).where(employeeTable.companyId.eq(companyId)).execute()
    }

    @Test
    fun `viewAllFeedback should be successful`() {
        val listFeedback = listOf(
            Feedback("Hi", 5L),
            Feedback("By", null)
        )
        feedbackDao.insertFeedback(listFeedback[0], companyId)
        feedbackDao.insertFeedback(listFeedback[1], companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        assertEquals(listFeedback.size, allFeedback.size)

        for (i in listFeedback.indices) {
            assertEquals(listFeedback[i].feedback, allFeedback[i].feedback)
            assertEquals(listFeedback[i].employeeId, allFeedback[i].employeeId)
        }
    }

    @Test
    fun `insertFeedback should be successful`() {
        val feedback = Feedback("Hi", 5L)
        val isInsert = feedbackDao.insertFeedback(feedback, companyId)
        assert(isInsert)
    }



    @Test
    fun `checkFeedbackStatus should be successful`() {
        val feedback = Feedback("Hi", 5L)
        feedbackDao.insertFeedback(feedback, companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        val feedbackStatus = feedbackDao.checkFeedbackStatus(allFeedback[0].id, 5L, companyId)
        assertNotNull(feedbackStatus)
        assertEquals(feedbackStatus?.let { FeedbackStatus.fromDatabaseValue(it) }, FeedbackStatus.UNREVIEWED)
    }


    @Test
    fun `checkFeedbackStatus should returns null`() {
        val feedback = Feedback("Hi", 5L)
        feedbackDao.insertFeedback(feedback, companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        val feedbackStatus = feedbackDao.checkFeedbackStatus(allFeedback[0].id, 5L, 1)
        assertNull(feedbackStatus)
    }

    @Test
    fun `updateFeedbackStatus should be successful`() {
        val feedback = Feedback("Hi", 5L)
        feedbackDao.insertFeedback(feedback, companyId)
        val allFeedback = feedbackDao.viewAllFeedback(companyId)
        val isUpdate = feedbackDao.updateFeedbackStatus(allFeedback[0].id, companyId, FeedbackStatus.REVIEWED)
        assert(isUpdate)
        val feedbackStatus = feedbackDao.checkFeedbackStatus(allFeedback[0].id, 5L, companyId)
        assertNotNull(feedbackStatus)
        assertEquals(feedbackStatus?.let { FeedbackStatus.fromDatabaseValue(it) }, FeedbackStatus.REVIEWED)
    }
}