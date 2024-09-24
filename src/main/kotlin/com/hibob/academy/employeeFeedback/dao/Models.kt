package com.hibob.academy.employeeFeedback.dao

import java.time.LocalDate

data class FeedbackData(
    val id: Long,
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val department: Department,
    val status: FeedbackStatus
)

data class Feedback(
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val department: Department,
    val status: FeedbackStatus
)

data class Employee(
    val firstName: String,
    val lastName: String,
    val role: Role,
    val companyId: Long,
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
    EMPLOYEE
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