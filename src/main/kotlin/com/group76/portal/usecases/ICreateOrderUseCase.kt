package com.group76.portal.usecases

import com.group76.portal.entities.request.CreateOrderRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.GetOrderResponse

interface ICreateOrderUseCase {
    fun execute(
        request: CreateOrderRequest
    ) : BaseResponse<GetOrderResponse>
}