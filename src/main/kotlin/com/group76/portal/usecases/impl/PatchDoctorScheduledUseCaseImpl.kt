package com.group76.portal.usecases.impl

import com.group76.portal.entities.DoctorAppointmentSetupEntity
import com.group76.portal.entities.request.PatchDoctorScheduleRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.PatchDoctorScheduleResponse
import com.group76.portal.gateways.IDoctorAppointmentSetupRepository
import com.group76.portal.usecases.IPatchDoctorScheduledUseCase
import org.springframework.stereotype.Service

@Service
class PatchDoctorScheduledUseCaseImpl(
    private val doctorAppointmentSetupRepository: IDoctorAppointmentSetupRepository
) : IPatchDoctorScheduledUseCase {
    override fun execute(request: PatchDoctorScheduleRequest)
    : BaseResponse<PatchDoctorScheduleResponse> {
        doctorAppointmentSetupRepository.save(
            DoctorAppointmentSetupEntity(
                price = request.price,
                doctorId = request.doctorId,

            )
        )
    }
}