package com.group76.portal.usecases.impl

import com.group76.portal.configuration.SystemProperties
import com.group76.portal.entities.AppointmentStatusEnum
import com.group76.portal.entities.request.OrderMessageSnsRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.GetOrderItemResponse
import com.group76.portal.entities.response.GetOrderResponse
import com.group76.portal.gateways.IOrderRepository
import com.group76.portal.services.ISnsService
import com.group76.portal.usecases.IUpdateOrderStatusUseCase
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateOrderStatusUseCaseImpl(
    private val orderRepository: IOrderRepository,
    private val snsService: ISnsService,
    private val systemProperties: SystemProperties
) : IUpdateOrderStatusUseCase {
    override fun execute(id: Long, status: AppointmentStatusEnum): BaseResponse<GetOrderResponse> {
        try {
            if (status == AppointmentStatusEnum.CANCELLED){
                return BaseResponse(
                    data = null,
                    error = BaseResponse.BaseResponseError("Order can't be cancelled in this method, use DELETE"),
                    statusCodes = HttpStatus.BAD_REQUEST
                )
            }
            val resultFind = orderRepository.findById(id)

            if (resultFind.isEmpty) return BaseResponse(
                data = null,
                error = BaseResponse.BaseResponseError("Order not found"),
                statusCodes = HttpStatus.BAD_REQUEST
            )

            val possibleStatus: Array<AppointmentStatusEnum> = when (resultFind.get().status) {
                AppointmentStatusEnum.PENDING -> arrayOf(
                    AppointmentStatusEnum.RECEIVED
                )
                AppointmentStatusEnum.CANCELLED -> arrayOf()
                AppointmentStatusEnum.RECEIVED -> arrayOf(AppointmentStatusEnum.READY)
                AppointmentStatusEnum.PREPARATION -> arrayOf(AppointmentStatusEnum.READY)
                AppointmentStatusEnum.READY -> arrayOf(AppointmentStatusEnum.FINISHED)
                AppointmentStatusEnum.FINISHED -> arrayOf()
            }

            if (!possibleStatus.contains(status)) {
                return BaseResponse(
                    data = null,
                    statusCodes = HttpStatus.BAD_REQUEST,
                    error = BaseResponse.BaseResponseError("Order status is ${resultFind.get().status} and can't be changed to $status")
                )
            }

            resultFind.get().updateStatus(status)
            val result = orderRepository.save(resultFind.get())

            snsService.publishMessage(
                topicArn = snsService.getTopicArnByName(systemProperties.sns.order)!!,
                message = OrderMessageSnsRequest(
                    orderId = result.id,
                    status = result.status
                ),
                subject = "Order Updated",
                id = result.id.toString()
            )

            return BaseResponse(
                data = GetOrderResponse(
                    createdDate = result.createdDate,
                    status = result.status,
                    clientId = UUID.fromString(result.clientId),
                    id = result.id,
                    totalPrice = result.totalPrice,
                    items = result.items.map {
                        GetOrderItemResponse(
                            productId = UUID.fromString(it.productId),
                            price = it.price,
                            quantity = it.quantity,
                            productName = it.productName
                        )
                    }
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