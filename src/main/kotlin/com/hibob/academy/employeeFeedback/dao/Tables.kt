package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.JooqTable

class FeedbackTable(tableName: String = "feedback") : JooqTable(tableName) {

    val id = createBigIntField("id")
    val feedback = createVarcharField("feedback")
    val employeeId = createBigIntField("employee_id")
    val companyId = createBigIntField("company_id")
    val date = createLocalDateField("date")
    val status = createVarcharField("status")

    companion object {
        val instance = FeedbackTable()
    }

}