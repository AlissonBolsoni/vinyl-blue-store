package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import br.com.alissonbolsoni.bluestore.dataprovider.entity.AlbumTable
import br.com.alissonbolsoni.bluestore.dataprovider.entity.GenreTable
import com.wrapper.spotify.model_objects.specification.Track

fun Track.toAlbum(genre: GenreTable) = AlbumTable(
    albumName = this.album.name,
    albumGenreTable = genre,
    albumPrice = AlbumTable.getRandomPrice()
)