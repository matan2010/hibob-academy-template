package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import org.springframework.stereotype.Component
import java.time.LocalDate
@Component
class ResponseDao(private val sql: DSLContext) {
    private val responseTable = ResponseTable.instance

    fun insertResponse(feedbackResponse: FeedbackResponse, employeeId: Long): Boolean {
        return sql.insertInto(responseTable)
            .set(responseTable.feedbackId, feedbackResponse.feedbackId)
            .set(responseTable.response, feedbackResponse.response)
            .set(responseTable.employeeId, employeeId)
            .set(responseTable.date, LocalDate.now())
            .execute() > 0
    }

}