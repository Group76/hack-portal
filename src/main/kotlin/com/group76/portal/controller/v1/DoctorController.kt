package com.group76.portal.controller.v1

import com.group76.portal.controller.v1.mapping.UrlMapping
import com.group76.portal.entities.request.DoctorAppointmentStatusRequest
import com.group76.portal.entities.request.DoctorAppointmentStatusValueRequest
import com.group76.portal.entities.request.PatchDoctorScheduleRequest
import com.group76.portal.entities.response.PatchDoctorScheduleResponse
import com.group76.portal.usecases.IPatchDoctorAppointmentUseCase
import com.group76.portal.usecases.IPatchDoctorScheduledUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlMapping.Version.V1 + UrlMapping.Resource.DOCTOR)
class DoctorController(
    val patchDoctorScheduleUseCase: IPatchDoctorScheduledUseCase,
    val patchDoctorAppointmentUseCase: IPatchDoctorAppointmentUseCase
) {
    @PatchMapping(
        name = "PatchDoctorSchedule",
        path = ["/schedule"]
    )
    @Operation(
        method = "PatchDoctorSchedule",
        description = "Create/update the schedule",
        responses = [
            ApiResponse(
                description = "OK", responseCode = "200", content = [
                    Content(schema = Schema(implementation = PatchDoctorScheduleResponse::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            )
        ]
    )
    fun patchDoctorSchedule(
        @Valid @RequestBody request: PatchDoctorScheduleRequest
    ): ResponseEntity<Any> {
        val response = patchDoctorScheduleUseCase.execute(request)

        return ResponseEntity(
            response.error ?: response.data,
            response.statusCodes
        )
    }

    @PatchMapping(
        name = "PatchAppointmentStatus",
        path = ["/appointment/status"]
    )
    @Operation(
        method = "PatchAppointmentStatus",
        description = "Update appointment status",
        responses = [
            ApiResponse(
                description = "OK", responseCode = "200", content = [
                    Content(schema = Schema(implementation = DoctorAppointmentStatusRequest::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            )
        ]
    )
    fun patchAppointmentStatus(
        @Valid @RequestBody request: DoctorAppointmentStatusRequest
    ): ResponseEntity<Any> {
        val response = patchDoctorAppointmentUseCase.execute(request)

        return ResponseEntity(
            response.error ?: response.data,
            response.statusCodes
        )
    }
}