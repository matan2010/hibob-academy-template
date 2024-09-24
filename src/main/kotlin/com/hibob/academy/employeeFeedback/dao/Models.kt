package com.hibob.academy.employeeFeedback.dao

import java.time.LocalDate

data class FeedbackData(
    val id: Long,
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val department: TechDepartment,
    val status: StatusFeedback
)

data class Feedback(
    val feedback: String,
    val employeeId: Long?,
    val companyId: Long,
    val data: LocalDate,
    val department: TechDepartment,
    val status: StatusFeedback
)

data class Employee(
    val firstName: String,
    val lastName: String,
    val role: TechRole,
    val companyId: Long,
)


enum class TechDepartment {
    DEVELOPMENT,
    IT,
    DATA_ANALYTICS,
    UX;

    fun toDatabaseValue(): String {
        return this.name.lowercase()
    }

    companion object {
        fun fromDatabaseValue(value: String): TechDepartment {
            return valueOf(value.uppercase())
        }
    }
}

enum class TechRole {
    ADMIN,
    HR,
    MANAGER,
    EMPLOYEE
}


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