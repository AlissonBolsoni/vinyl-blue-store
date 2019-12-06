package br.com.alissonbolsoni.bluestore.entrypoint.dto

import javax.validation.constraints.Min

data class OrderResponseDto(
    val orderId: Int = 0,
    val orderTotalValue: Double = 0.0,
    val orderTotalCashback: Double = 0.0,
    val message: String = ""
):IsPageableDto