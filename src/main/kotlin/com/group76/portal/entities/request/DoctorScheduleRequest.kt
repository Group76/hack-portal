package com.group76.portal.entities.request

import com.group76.portal.entities.dto.DoctorScheduleAvailableHours

data class DoctorScheduleRequest (
    val monday: DoctorAvailableHoursRequest,
    val tuesday: DoctorAvailableHoursRequest,
    val wednesday: DoctorAvailableHoursRequest,
    val thursday: DoctorAvailableHoursRequest,
    val friday: DoctorAvailableHoursRequest,
    val saturday: DoctorAvailableHoursRequest,
    val sunday: DoctorAvailableHoursRequest
)