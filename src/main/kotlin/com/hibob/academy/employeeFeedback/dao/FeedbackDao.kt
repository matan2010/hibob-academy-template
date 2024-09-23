package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.dao.OwnerData
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import org.springframework.stereotype.Component

@Component
class FeedbackDao(private val sql: DSLContext) {
    private val feedbackTable = FeedbackTable.instance

    private val feedbackMapper = RecordMapper<Record, FeedbackData> { record ->
        FeedbackData(
            record[feedbackTable.id],
            record[feedbackTable.feedback],
            record[feedbackTable.employeeId],
            record[feedbackTable.companyId],
            record[feedbackTable.date],
            record[feedbackTable.department],
            record[feedbackTable.status]
        )
    }

    fun viewAllFeedback(companyId: Long): List<FeedbackData> {
        return sql.select(
            feedbackTable.id,
            feedbackTable.feedback,
            feedbackTable.employeeId,
            feedbackTable.companyId,
            feedbackTable.date,
            feedbackTable.department,
            feedbackTable.status
        )
            .from(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .fetch(feedbackMapper)
    }

    fun insertFeedback(feedback: Feedback): Boolean {
        return sql.insertInto(feedbackTable)
            .set(feedbackTable.feedback, feedback.feedback)
            .set(feedbackTable.employeeId, feedback.employeeId)
            .set(feedbackTable.companyId, feedback.companyId)
            .set(feedbackTable.date, feedback.data)
            .set(feedbackTable.department, feedback.department)
            .set(feedbackTable.status, feedback.status)
            .execute() > 0
    }

}