package com.group76.portal.usecases.impl

import com.group76.portal.entities.response.BaseResponse
import com.group76.portal.entities.response.GetOrderItemResponse
import com.group76.portal.entities.response.GetOrderResponse
import com.group76.portal.gateways.IOrderRepository
import com.group76.portal.usecases.IGetOrderUseCase
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetOrderUseCaseImpl(
    private val orderRepository: IOrderRepository,
): IGetOrderUseCase {
    override fun execute(id: Long): BaseResponse<GetOrderResponse> {
        val result = orderRepository.findById(id)

        if (result.isEmpty) return BaseResponse(
            data = null,
            error = BaseResponse.BaseResponseError("Order not found"),
            statusCodes = HttpStatus.BAD_REQUEST
        )

        val response = result.map {
            BaseResponse(
                data = GetOrderResponse(
                    createdDate = it.createdDate,
                    status = it.status,
                    clientId = UUID.fromString(it.clientId),
                    id = it.id,
                    totalPrice = it.totalPrice,
                    items = it.items.map {item ->
                        GetOrderItemResponse(
                            productId = UUID.fromString(item.productId),
                            price = item.price,
                            quantity = item.quantity,
                            productName = item.productName
                        )
                    }
                ),
                error = null
            )
        }

        return response.get()
    }
}