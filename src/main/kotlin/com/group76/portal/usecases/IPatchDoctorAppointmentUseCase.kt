package com.group76.portal.usecases

import com.group76.portal.entities.request.DoctorAppointmentStatusRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.PatchDoctorScheduleResponse

interface IPatchDoctorAppointmentUseCase {
    fun execute(
        request: DoctorAppointmentStatusRequest
    ) : BaseResponse<PatchDoctorScheduleResponse>
}