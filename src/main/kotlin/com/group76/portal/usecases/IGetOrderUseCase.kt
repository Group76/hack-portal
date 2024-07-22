package com.group76.portal.usecases

import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.GetOrderResponse

interface IGetOrderUseCase {
    fun execute(
        id: Long
    ) : BaseResponse<GetOrderResponse>
}