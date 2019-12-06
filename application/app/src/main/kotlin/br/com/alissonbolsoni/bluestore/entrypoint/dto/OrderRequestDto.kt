package br.com.alissonbolsoni.bluestore.entrypoint.dto

import javax.validation.constraints.Min

data class OrderRequestDto(
    val albums: List<Int>
)