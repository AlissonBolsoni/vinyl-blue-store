package br.com.alissonbolsoni.bluestore.core.entity

import java.util.*

open class Order(
    val id: Int = 0,
    val total: Double = 0.0,
    val cashback: Double = 0.0,
    val date: Date = Date(),
    val albumsOrdered: Map<Album, Cashback>? = null
) : IsPageable {}