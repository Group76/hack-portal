package com.group76.portal.entities.response

import com.group76.portal.entities.request.DoctorAvailableHoursRequest

data class DoctorScheduleResponse(
    val monday: DoctorAvailableHoursRequest,
    val tuesday: DoctorAvailableHoursRequest,
    val wednesday: DoctorAvailableHoursRequest,
    val thursday: DoctorAvailableHoursRequest,
    val friday: DoctorAvailableHoursRequest,
    val saturday: DoctorAvailableHoursRequest,
    val sunday: DoctorAvailableHoursRequest
)
