package com.group76.portal.entities.request

data class DoctorAppointmentStatusRequest(
    val appointmentId: Long,
    val status: DoctorAppointmentStatusValueRequest
)
