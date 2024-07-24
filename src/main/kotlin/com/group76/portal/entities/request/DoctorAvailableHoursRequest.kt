package com.group76.portal.entities.request

import java.time.OffsetTime

data class DoctorAvailableHoursRequest (
    val availableHours: List<OffsetTime>
)