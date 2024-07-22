package com.group76.portal.usecases

import com.group76.portal.entities.AppointmentStatusEnum
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.GetOrderResponse

interface IUpdateOrderStatusUseCase {
    fun execute(
        id: Long,
        status: AppointmentStatusEnum
    ): BaseResponse<GetOrderResponse>
}