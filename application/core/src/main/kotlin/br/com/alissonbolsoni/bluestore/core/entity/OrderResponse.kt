package br.com.alissonbolsoni.bluestore.core.entity

import java.util.*

open class OrderResponse(
    val orderId: Int,
    val total: Double,
    val cashback: Double = 0.0
):IsPageable
