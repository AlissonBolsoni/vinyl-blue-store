package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import br.com.alissonbolsoni.bluestore.core.entity.Cashback
import br.com.alissonbolsoni.bluestore.dataprovider.entity.CashbackGenreTable

fun List<CashbackGenreTable>.toEntity(): List<Cashback> {
    val list = ArrayList<Cashback>()
    this.forEach {
        list.add(it.toEntity())
    }
    return list
}

fun CashbackGenreTable.toEntity() = Cashback(
    this.cashbackId ?: 0,
    this.cashbackGenreTable?.toEntity(),
    this.cashbackDayOfWeek,
    this.cashbackValue
)