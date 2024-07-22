package com.group76.portal.entities.dto

data class DoctorScheduleDto(
    val monday: DoctorScheduleAvailableHours,
    val tuesday: DoctorScheduleAvailableHours,
    val wednesday: DoctorScheduleAvailableHours,
    val thursday: DoctorScheduleAvailableHours,
    val friday: DoctorScheduleAvailableHours,
    val saturday: DoctorScheduleAvailableHours,
    val sunday: DoctorScheduleAvailableHours
)