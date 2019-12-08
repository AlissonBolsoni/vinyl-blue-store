package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.dataprovider.entity.AlbumOrderedTable
import br.com.alissonbolsoni.bluestore.dataprovider.entity.AlbumTable
import br.com.alissonbolsoni.bluestore.entrypoint.dto.AlbumDTO


fun List<Album>.toAlbumDTO(): List<AlbumDTO> {
    val list = ArrayList<AlbumDTO>()
    this.forEach {
        list.add(it.toAlbumDTO())
    }
    return list
}

fun Album.toAlbumDTO() = AlbumDTO(
    this.id,
    this.name,
    this.price,
    this.genre?.name ?: ""
)

fun List<AlbumTable>.toEntity(): List<Album> {
    val list = ArrayList<Album>()
    this.forEach {
        list.add(it.toEntity())
    }
    return list
}

fun AlbumTable.toEntity(): Album {
    val album = Album(
        this.albumId ?: 0,
        this.albumName,
        this.albumPrice
    )

    album.genre = this.albumGenreTable?.toEntity()
    return album
}

fun Album.toAlbumOrderedTable() = AlbumOrderedTable(
    albumTable = this.toTable(),
    albumOrderedPrice = this.price
)

fun Album.toTable() = AlbumTable(
    this.id,
    this.name,
    this.price,
    this.genre?.toTable()
)