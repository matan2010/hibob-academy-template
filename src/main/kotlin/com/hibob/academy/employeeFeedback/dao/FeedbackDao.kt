package com.hibob.academy.employeeFeedback.dao

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
            TechDepartment.fromDatabaseValue(record[feedbackTable.department]),
            StatusFeedback.fromDatabaseValue(record[feedbackTable.status])
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
            .set(feedbackTable.department, feedback.department.toDatabaseValue())
            .set(feedbackTable.status, feedback.status.toDatabaseValue())
            .execute() > 0
    }

}