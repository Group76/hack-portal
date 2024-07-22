package com.group76.portal.usecases.impl

import com.group76.portal.configuration.SystemProperties
import com.group76.portal.entities.AppointmentStatusEnum
import com.group76.portal.entities.request.CancelOrderRequest
import com.group76.portal.entities.request.OrderCancelledMessageSnsRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.CancelResponse
import com.group76.portal.gateways.IOrderRepository
import com.group76.portal.services.ISnsService
import com.group76.portal.usecases.ICancelOrderUseCase
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CancelOrderUseCaseImpl(
    private val orderRepository: IOrderRepository,
    private val snsService: ISnsService,
    private val systemProperties: SystemProperties
): ICancelOrderUseCase {
    override fun execute(request: CancelOrderRequest): BaseResponse<CancelResponse> {
        try {
            val resultFind = orderRepository.findById(request.id)

            if (resultFind.isEmpty) return BaseResponse(
                data = null,
                error = BaseResponse.BaseResponseError("Order not found"),
                statusCodes = HttpStatus.BAD_REQUEST
            )

            if (resultFind.get().status != AppointmentStatusEnum.PENDING) {
                return BaseResponse(
                    data = null,
                    statusCodes = HttpStatus.BAD_REQUEST,
                    error = BaseResponse.BaseResponseError("Order status is ${resultFind.get().status} and can't be cancelled")
                )
            }

            resultFind.get().cancelled(request.reason)
            val result = orderRepository.save(resultFind.get())

            snsService.publishMessage(
                topicArn = snsService.getTopicArnByName(systemProperties.sns.orderCancelled)!!,
                message = OrderCancelledMessageSnsRequest(
                    orderId = result.id,
                    reason = result.cancelledReason!!
                ),
                subject = "Order Cancelled",
                id = result.id.toString()
            )

            return BaseResponse(
                data = CancelResponse(
                    success = true
                ),
                error = null
            )
        } catch (ex: Exception) {
            return BaseResponse(
                data = null,
                statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                error = BaseResponse.BaseResponseError("Internal server error")
            )
        }
    }
}