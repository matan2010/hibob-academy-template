package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
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
    fun `insertFeedback should be successful`() {
        val feedback1 = Feedback("Hi", 5L, companyId, LocalDate.now(), "Dev", "UNREVIEWED")
        val feedback2 = Feedback("By", 6L, companyId, LocalDate.now(), "Hr", "UNREVIEWED")
        dao.insertFeedback(feedback1)
        dao.insertFeedback(feedback2)
    }

}