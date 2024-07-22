package com.group76.portal.entities.dto

import java.time.OffsetTime

data class DoctorScheduleAvailableHours(
 val availableHours: List<OffsetTime>
)
