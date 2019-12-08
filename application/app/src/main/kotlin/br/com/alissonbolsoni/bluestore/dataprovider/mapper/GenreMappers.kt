package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import br.com.alissonbolsoni.bluestore.core.entity.Genre
import br.com.alissonbolsoni.bluestore.dataprovider.entity.GenreTable

fun GenreTable.toEntity() = Genre(
    this.genreId ?: 0, this.genreName
)

fun Genre.toTable() = GenreTable(
    this.id, this.name
)