package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import org.springframework.stereotype.Component
import java.time.LocalDate

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
            FeedbackStatus.fromDatabaseValue(record[feedbackTable.status])
        )
    }

    fun viewAllFeedback(companyId: Long): List<FeedbackData> {
        return sql.select()
            .from(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .fetch(feedbackMapper)
    }

    fun insertFeedback(feedback: Feedback,companyId: Long): Boolean {
        return sql.insertInto(feedbackTable)
            .set(feedbackTable.feedback, feedback.feedback)
            .set(feedbackTable.employeeId, feedback.employeeId)
            .set(feedbackTable.companyId, companyId)
            .set(feedbackTable.date, LocalDate.now())
            .set(feedbackTable.status, FeedbackStatus.UNREVIEWED.toDatabaseValue())
            .execute() > 0
    }

    fun updateFeedbackStatus(feedbackId: Long, companyId: Long, feedbackStatus: FeedbackStatus): Boolean {
        return sql.update(feedbackTable)
            .set(feedbackTable.status, feedbackStatus.toDatabaseValue())
            .where(feedbackTable.id.eq(feedbackId))
            .and(feedbackTable.companyId.eq(companyId))
            .execute() > 0
    }

    fun checkFeedbackStatus(feedbackId: Long, employeeId: Long, companyId: Long): String? {
        return sql.select(feedbackTable.status)
            .from(feedbackTable)
            .where(feedbackTable.companyId.eq(companyId))
            .and(feedbackTable.employeeId.eq(employeeId))
            .and(feedbackTable.id.eq(feedbackId))
            .fetchOne(feedbackTable.status)
    }

}