package com.hibob.academy.employeeFeedback.resource

import com.hibob.academy.employeeFeedback.dao.EmployeeData
import com.hibob.academy.employeeFeedback.dao.Role
import com.hibob.academy.filters.AuthenticationFilter
import jakarta.ws.rs.NotAuthorizedException
import jakarta.ws.rs.container.ContainerRequestContext

abstract class BaseResource {

    protected fun getEmployeeData(requestContext: ContainerRequestContext): EmployeeData {
        return requestContext.getProperty(AuthenticationFilter.EMPLOYEE) as EmployeeData?
            ?: throw NotAuthorizedException("Unauthorized access.")
    }

    private fun checkRole(employeeData: EmployeeData, vararg allowedRoles: Role) {
        if (employeeData.role !in allowedRoles) {
            throw NotAuthorizedException("You do not have permission to perform this action.")
        }
    }

    protected fun checkHR(employeeData: EmployeeData) {
        checkRole(employeeData, Role.HR)
    }

    protected fun checkHROrManager(employeeData: EmployeeData) {
        checkRole(employeeData, Role.HR, Role.MANAGER)
    }
}