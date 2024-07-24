package com.group76.portal.entities.request

import com.group76.portal.entities.dto.DoctorScheduleDto
import java.util.UUID

data class PatchDoctorScheduleRequest(
    val doctorId: UUID,
    val price: Double,
    val schedules: DoctorScheduleRequest
)
