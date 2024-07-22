package com.group76.portal.entities.request

import com.group76.portal.entities.AppointmentStatusEnum

data class OrderMessageSnsRequest (
    val orderId: Long,
    val status: AppointmentStatusEnum
)