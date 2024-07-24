package com.group76.portal.usecases

import com.group76.portal.entities.request.PatchDoctorScheduleRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.PatchDoctorScheduleResponse

interface IPatchDoctorScheduledUseCase {
    fun execute(
        request: PatchDoctorScheduleRequest
    ) : BaseResponse<PatchDoctorScheduleResponse>
}