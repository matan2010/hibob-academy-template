package com.hibob.academy.employeeFeedback.dao

import com.hibob.academy.utils.BobDbTest
import org.jooq.DSLContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@BobDbTest
class EmployeeDaoTest @Autowired constructor(private val sql: DSLContext) {
    private val dao = EmployeeDao(sql)


    @Test
    fun `getEmployee should be successful`() {
        val employee = Employee("Ross", "Geller", 1L)
        val employeeDao = dao.getEmployee(employee)
        assertNotNull(employeeDao)
    }
}