package com.group76.portal.usecases.impl

import com.group76.portal.entities.request.DoctorAppointmentStatusRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.PatchDoctorScheduleResponse
import com.group76.portal.usecases.IPatchDoctorAppointmentUseCase

class PatchDoctorAppointmentUseCaseImpl: IPatchDoctorAppointmentUseCase {
    override fun execute(request: DoctorAppointmentStatusRequest)
    : BaseResponse<PatchDoctorScheduleResponse> {
        TODO("Not yet implemented")
    }
}