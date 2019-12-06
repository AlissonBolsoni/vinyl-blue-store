package br.com.alissonbolsoni.bluestore.core.mapper

import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.OrderResponse

fun List<Order>.toOrderResponse(): List<OrderResponse> =
    this.map { it.toOrderResponse() }.toList()

fun Order.toOrderResponse() = OrderResponse(
    orderId = this.id,
    total = this.total,
    cashback = this.cashback
)