package com.hibob.academy.employeeFeedback.service

import com.hibob.academy.employeeFeedback.dao.FeedbackResponse
import com.hibob.academy.employeeFeedback.dao.ResponseDao
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ResponseServiceTest(){
    private val responseDaoMok = mock<ResponseDao> {}
    private val responseService = ResponseService(responseDaoMok)


    @Test
    fun `insertResponse should be successful`() {
        val response= FeedbackResponse(1L,"Hi")
        whenever(responseDaoMok.insertResponse(response,7L)).thenReturn(true)
        responseService.insertResponse(response,7L)
        verify(responseDaoMok).insertResponse(response,7L)
    }
}