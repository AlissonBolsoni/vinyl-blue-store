package br.com.alissonbolsoni.bluestore.core.entity


data class Album(
    val id: Int,
    val name: String = "",
    val price: Double = 0.0
) : IsPageable{
    var genre: Genre? = null
}