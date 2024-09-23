package com.hibob.academy.employeeFeedback.dao

import java.time.LocalDate

data class FeedbackData(
    val id: Long,
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val department: String,
    val status: String
)

data class Feedback(
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val department: String,
    val status: String
)

enum class StatusFeedback {
    REVIEWED,
    UNREVIEWED;

    fun toDatabaseValue(): String {
        return this.name.lowercase()
    }

    companion object {
        fun fromDatabaseValue(value: String): StatusFeedback {
            return valueOf(value.uppercase())
        }
    }

}