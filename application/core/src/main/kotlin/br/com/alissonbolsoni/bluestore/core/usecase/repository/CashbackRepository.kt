package br.com.alissonbolsoni.bluestore.core.usecase.repository

import br.com.alissonbolsoni.bluestore.core.entity.Cashback

interface CashbackRepository {

    fun getCashbackByDayOfWeek(dayOfWeek: Int):List<Cashback>
}