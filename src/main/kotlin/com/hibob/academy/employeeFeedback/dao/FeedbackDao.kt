package com.hibob.academy.employeeFeedback.dao

import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class FeedbackDao(private val sql: DSLContext) {
    private val feedbackTable = FeedbackTable.instance
}