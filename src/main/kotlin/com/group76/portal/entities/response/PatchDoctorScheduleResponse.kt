package com.group76.portal.entities.response

import com.group76.portal.entities.request.DoctorScheduleRequest
import java.util.*

data class PatchDoctorScheduleResponse (
    val doctorId: UUID,
    val price: Double,
    val schedules: DoctorScheduleRequest
)