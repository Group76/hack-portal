package com.group76.portal.entities

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "appointment")
data class AppointmentEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val patientId: String = "",
    val doctorId: String = "",

    @Enumerated(EnumType.STRING)
    var status: AppointmentStatusEnum = AppointmentStatusEnum.PENDING,

    val createdDate: OffsetDateTime = OffsetDateTime.now(),
    var updatedDate: OffsetDateTime = OffsetDateTime.now(),
    val appointmentDate: OffsetDateTime = OffsetDateTime.now(),
    val onlineMeetUrl: String,
    var cancelledReason: String? = null
){
    fun updateStatus(status: AppointmentStatusEnum){
        this.status = status
        this.updatedDate = OffsetDateTime.now()
    }

    fun cancelled(reason: String){
        this.status = AppointmentStatusEnum.CANCELLED
        this.updatedDate = OffsetDateTime.now()
        cancelledReason = reason
    }
}