package com.group76.portal.entities.response

import com.group76.portal.entities.AppointmentStatusEnum
import java.time.OffsetDateTime
import java.util.*

data class GetOrderResponse(
    val id: Long,
    val clientId: UUID,
    val status: AppointmentStatusEnum,
    val totalPrice: Double,
    val createdDate: OffsetDateTime,
    val items: List<GetOrderItemResponse>
)
