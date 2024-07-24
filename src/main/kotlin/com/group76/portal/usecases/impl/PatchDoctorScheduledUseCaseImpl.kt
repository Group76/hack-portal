package com.group76.portal.usecases.impl

import com.group76.portal.entities.DoctorAppointmentSetupEntity
import com.group76.portal.entities.dto.DoctorScheduleAvailableHours
import com.group76.portal.entities.dto.DoctorScheduleDto
import com.group76.portal.entities.request.PatchDoctorScheduleRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.DoctorAvailableHoursResponse
import com.group76.portal.entities.response.DoctorScheduleResponse
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
        val entity = DoctorAppointmentSetupEntity(
            price = request.price,
            doctorId = request.doctorId,
            schedules = DoctorScheduleDto(
                friday = DoctorScheduleAvailableHours(request.schedules.friday.availableHours),
                monday = DoctorScheduleAvailableHours(request.schedules.monday.availableHours),
                wednesday = DoctorScheduleAvailableHours(request.schedules.wednesday.availableHours),
                sunday = DoctorScheduleAvailableHours(request.schedules.sunday.availableHours),
                saturday = DoctorScheduleAvailableHours(request.schedules.saturday.availableHours),
                thursday = DoctorScheduleAvailableHours(request.schedules.thursday.availableHours),
                tuesday = DoctorScheduleAvailableHours(request.schedules.tuesday.availableHours)
            )
        )
        doctorAppointmentSetupRepository.save(
            entity
        )

        return BaseResponse(
            error = null,
            data = PatchDoctorScheduleResponse(
                doctorId = entity.doctorId,
                price = entity.price,
                schedules = DoctorScheduleResponse(
                    tuesday = DoctorAvailableHoursResponse(entity.schedules.tuesday.availableHours),
                    monday = DoctorAvailableHoursResponse(entity.schedules.monday.availableHours),
                    sunday = DoctorAvailableHoursResponse(entity.schedules.sunday.availableHours),
                    saturday = DoctorAvailableHoursResponse(entity.schedules.saturday.availableHours),
                    thursday = DoctorAvailableHoursResponse(entity.schedules.thursday.availableHours),
                    friday = DoctorAvailableHoursResponse(entity.schedules.friday.availableHours),
                    wednesday = DoctorAvailableHoursResponse(entity.schedules.wednesday.availableHours),
                )
            ),
        )
    }
}