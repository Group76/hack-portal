package com.group76.portal.gateways

import com.group76.portal.entities.AppointmentEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IOrderRepository: CrudRepository<AppointmentEntity, Long> {
}