package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.RecordMapper
import org.jooq.SelectConditionStep
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class FeedbackDao(private val sql: DSLContext) {
    private val feedbackTable = FeedbackTable.instance
    private val employeeTable = EmployeeTable.instance

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

    fun getFeedbackByParams(companyId: Long, params: FeedbackQueryParams): List<FeedbackData> {
        val baseQuery = sql.select()
            .from(feedbackTable)
            .leftJoin(employeeTable).on(feedbackTable.employeeId.eq(employeeTable.id))
            .where(feedbackTable.companyId.eq(companyId))

        val query = baseQuery
            .let { q -> handleDepartmentCondition(params, q) }
            .let { q -> handleEmployeeIdCondition(params, q) }
            .let { q -> handleDateCondition(params, q) }

        return query.fetch(feedbackMapper)
    }

    private fun handleDateCondition(
        params: FeedbackQueryParams,
        q: SelectConditionStep<Record>
    ) = params.date?.let {
        q.and(feedbackTable.date.eq(it))
    } ?: q

    private fun handleEmployeeIdCondition(
        params: FeedbackQueryParams,
        q: SelectConditionStep<Record>
    ) = if (params.nullEmployeeId) {
        q.and(feedbackTable.employeeId.isNull)
    } else {
        q
    }

    private fun handleDepartmentCondition(
        params: FeedbackQueryParams,
        q: SelectConditionStep<Record>
    ) = params.department?.let {
        q.and(employeeTable.department.eq(it.name.lowercase()))
    } ?: q


}