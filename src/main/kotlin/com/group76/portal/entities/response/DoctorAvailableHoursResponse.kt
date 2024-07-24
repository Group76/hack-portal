package com.group76.portal.entities.response

import java.time.OffsetTime

data class DoctorAvailableHoursResponse (
    val availableHours: List<OffsetTime>
)