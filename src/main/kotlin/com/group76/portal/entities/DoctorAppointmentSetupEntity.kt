package com.group76.portal.entities

import com.group76.portal.entities.dto.DoctorScheduleDto
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "doctor_appointment_setup")
data class DoctorAppointmentSetupEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val doctorId: UUID,

    val price: Double = 0.0,
    val schedules: DoctorScheduleDto
)
