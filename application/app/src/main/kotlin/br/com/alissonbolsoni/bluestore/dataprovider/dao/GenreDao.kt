package br.com.alissonbolsoni.bluestore.dataprovider.dao

import br.com.alissonbolsoni.bluestore.core.entity.Genre
import br.com.alissonbolsoni.bluestore.dataprovider.entity.CashbackGenreTable
import br.com.alissonbolsoni.bluestore.dataprovider.entity.GenreTable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreDao : CrudRepository<GenreTable, Int> {
    fun findByGenreNameIgnoreCase(genre: String): GenreTable?
}