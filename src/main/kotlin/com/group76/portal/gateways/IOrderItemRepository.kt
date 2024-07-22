package com.group76.portal.gateways

import com.group76.portal.entities.DoctorAppointmentSetupEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface IOrderItemRepository: CrudRepository<DoctorAppointmentSetupEntity, UUID> {
}