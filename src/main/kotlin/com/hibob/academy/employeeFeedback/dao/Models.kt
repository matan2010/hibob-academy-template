package com.hibob.academy.employeeFeedback.dao

import java.time.LocalDate

data class FeedbackData(
    val id: Long,
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val status: FeedbackStatus
)

data class Feedback(
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val status: FeedbackStatus
)

data class EmployeeData(
    val id: Long,
    val role: Role,
    val companyId: Long,
    val department: Department
)

data class Employee(
    val firstName: String,
    val lastName: String,
    val companyId: Long
)

data class ResponseFeedbackData(
    val feedbackId: Long,
    val response: String,
    val employeeId: Long,
    val data: LocalDate,
)

data class FeedbackResponse(
    val feedbackId: Long,
    val response: String
)

enum class Department {
    DEVELOPMENT,
    IT,
    DATA_ANALYTICS,
    UX;

    fun toDatabaseValue(): String {
        return this.name.lowercase()
    }

    companion object {
        fun fromDatabaseValue(value: String): Department {
            return valueOf(value.uppercase())
        }
    }
}

enum class Role {
    ADMIN,
    HR,
    MANAGER,
    EMPLOYEE;

    fun toDatabaseValue(): String {
        return this.name.lowercase()
    }

    companion object {
        fun fromDatabaseValue(value: String): Role {
            return valueOf(value.uppercase())
        }
    }
}


enum class FeedbackStatus {
    REVIEWED,
    UNREVIEWED;

    fun toDatabaseValue(): String {
        return this.name.lowercase()
    }

    companion object {
        fun fromDatabaseValue(value: String): FeedbackStatus {
            return valueOf(value.uppercase())
        }
    }

}