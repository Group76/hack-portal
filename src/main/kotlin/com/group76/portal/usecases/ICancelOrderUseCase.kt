package com.group76.portal.usecases

import com.group76.portal.entities.request.CancelOrderRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.CancelResponse

interface ICancelOrderUseCase {
    fun execute(
        request: CancelOrderRequest
    ): BaseResponse<CancelResponse>
}