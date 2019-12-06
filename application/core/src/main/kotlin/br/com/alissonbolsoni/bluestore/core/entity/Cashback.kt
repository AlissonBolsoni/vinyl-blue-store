package br.com.alissonbolsoni.bluestore.core.entity

data class Cashback(
    val id: Int = 0,
    val genre: Genre? = null,
    val dayOfWeek: Int = 1,
    val value: Double = 0.0,
    var price: Double = 0.0
)