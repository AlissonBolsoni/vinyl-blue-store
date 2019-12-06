package br.com.alissonbolsoni.bluestore.entrypoint.dto

import br.com.alissonbolsoni.bluestore.core.entity.Album

class AlbumDTO (
    val albumId: Int,
    val albumName: String,
    val albumPrice: Double,
    val genre: String
):IsPageableDto