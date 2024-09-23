package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

@BobDbTest
class FeedbackDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val companyId: Long = 8L
    private val table = FeedbackTable.instance
    private val dao = FeedbackDao(sql)

    @BeforeEach
    @AfterEach
    fun cleanup() {
        sql.deleteFrom(table).execute()
    }

    @Test
    fun `viewAllFeedback should be successful`() {
        val listFeedback = listOf(
            Feedback("Hi", 5L, companyId, LocalDate.now(), "Dev", "UNREVIEWED"),
            Feedback("By", null, companyId, LocalDate.now(), "Hr", "UNREVIEWED")
        )
        dao.insertFeedback(listFeedback[0])
        dao.insertFeedback(listFeedback[1])
        val allFeedback = dao.viewAllFeedback(companyId)
        assertEquals(listFeedback.size, allFeedback.size)
        for (i in listFeedback.indices) {
            assertEquals(listFeedback[i].feedback, allFeedback[i].feedback)
            assertEquals(listFeedback[i].employeeId, allFeedback[i].employeeId)
            assertEquals(listFeedback[i].companyId, allFeedback[i].companyId)
            assertEquals(listFeedback[i].data, allFeedback[i].data)
            assertEquals(listFeedback[i].status, allFeedback[i].status)
        }
    }

    @Test
    fun `insertFeedback should be successful`() {
        val feedback = Feedback("Hi", 5L, companyId, LocalDate.now(), "Dev", "UNREVIEWED")
        val isInsert = dao.insertFeedback(feedback)
        assert(isInsert)
    }
}