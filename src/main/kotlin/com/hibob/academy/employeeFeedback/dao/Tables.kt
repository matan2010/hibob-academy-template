package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.JooqTable
import com.sun.java.swing.ui.CommonUI.createTextField

class FeedbackTable(tableName: String = "feedback") : JooqTable(tableName) {

    val id = createBigIntField("id")
    val feedback = createTextField("feedback")
    val employeeId = createBigIntField("employee_id")
    val companyId = createBigIntField("company_id")
    val date = createDateField("date")

    companion object {
        val instance = FeedbackTable()
    }

}