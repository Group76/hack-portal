package com.group76.portal.entities.request

import com.group76.portal.entities.AppointmentStatusEnum

data class OrderCancelledMessageSnsRequest (
    val orderId: Long,
    val status: AppointmentStatusEnum = AppointmentStatusEnum.CANCELLED,
    val reason: String
)