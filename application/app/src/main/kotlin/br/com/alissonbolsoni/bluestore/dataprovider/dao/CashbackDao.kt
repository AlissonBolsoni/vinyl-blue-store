package br.com.alissonbolsoni.bluestore.dataprovider.dao

import br.com.alissonbolsoni.bluestore.dataprovider.entity.CashbackGenreTable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CashbackDao : CrudRepository<CashbackGenreTable, Int> {

    fun findByCashbackDayOfWeek(dayOfWeek: Int): List<CashbackGenreTable>
}