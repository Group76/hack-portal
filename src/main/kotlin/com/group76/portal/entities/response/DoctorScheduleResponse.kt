package com.group76.portal.entities.response

data class DoctorScheduleResponse(
    val monday: DoctorAvailableHoursResponse,
    val tuesday: DoctorAvailableHoursResponse,
    val wednesday: DoctorAvailableHoursResponse,
    val thursday: DoctorAvailableHoursResponse,
    val friday: DoctorAvailableHoursResponse,
    val saturday: DoctorAvailableHoursResponse,
    val sunday: DoctorAvailableHoursResponse
)
