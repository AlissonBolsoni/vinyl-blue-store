package br.com.alissonbolsoni.bluestore.entrypoint.dto

import java.util.*

data class OrderDto(
    val id: Int = 0,
    val total: Double = 0.0,
    val cashback: Double = 0.0,
    val date: Date = Date(),
    val albumsOrdered: List<AlbumDTO> = ArrayList()
):IsPageableDto{
    var message = ""
}
