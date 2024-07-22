package com.group76.portal.entities

import com.group76.portal.entities.dto.DoctorScheduleDto
import jakarta.persistence.*

@Entity
@Table(name = "doctor_appointment_setup")
data class DoctorAppointmentSetupEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val doctorId: String,

    val price: Double = 0.0,
    val schedules: DoctorScheduleDto
)
