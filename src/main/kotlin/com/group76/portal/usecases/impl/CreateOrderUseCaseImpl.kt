package com.group76.portal.usecases.impl

import com.group76.portal.configuration.SystemProperties
import com.group76.portal.entities.AppointmentEntity
import com.group76.portal.entities.DoctorAppointmentSetupEntity
import com.group76.portal.entities.AppointmentStatusEnum
import com.group76.portal.entities.request.CreateOrderRequest
import com.group76.portal.entities.request.OrderMessageSnsRequest
import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.GetOrderItemResponse
import com.group76.portal.entities.response.GetOrderResponse
import com.group76.portal.gateways.IOrderRepository
import com.group76.portal.services.ISnsService
import com.group76.portal.usecases.ICreateOrderUseCase
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

@Service
class CreateOrderUseCaseImpl(
    private val orderRepository: IOrderRepository,
    private val snsService: ISnsService,
    private val systemProperties: SystemProperties,
) : ICreateOrderUseCase {
    override fun execute(request: CreateOrderRequest): BaseResponse<GetOrderResponse> {
        try {
            val error = request.getError()
            if(error != null) return BaseResponse(
                data = null,
                error = BaseResponse.BaseResponseError(error),
                HttpStatus.BAD_REQUEST
            )

            val order = AppointmentEntity(
                createdDate = OffsetDateTime.now(),
                totalPrice = 0.0,
                updatedDate = OffsetDateTime.now(),
                status = AppointmentStatusEnum.PENDING,
                clientId = request.clientId.toString()
            )

            val items = mutableListOf<DoctorAppointmentSetupEntity>()
            var totalPrice = 0.0
            request.items.map {
                totalPrice = totalPrice.plus((it.price * it.quantity))

                items.add(
                    DoctorAppointmentSetupEntity(
                        order = order,
                        price = it.price,
                        productId = it.productId.toString(),
                        productName = it.productName,
                        quantity = it.quantity
                    )
                )
            }

            order.totalPrice = totalPrice
            order.items = items


            val result = orderRepository.save(order)

            snsService.publishMessage(
                topicArn = snsService.getTopicArnByName(systemProperties.sns.order)!!,
                message = OrderMessageSnsRequest(
                    orderId = result.id,
                    status = result.status
                ),
                subject = "Order Created",
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
        }
        catch (ex: Exception){
            return BaseResponse(
                data = null,
                statusCodes = HttpStatus.INTERNAL_SERVER_ERROR,
                error = BaseResponse.BaseResponseError("Internal server error")
            )
        }
    }
}