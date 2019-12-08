package br.com.alissonbolsoni.bluestore.entrypoint.dto

class AlbumDTO (
    val albumId: Int = 0,
    val albumName: String = "",
    val albumPrice: Double = 0.0,
    val genre: String = ""
):IsPageableDto{
    var message: String = ""
}