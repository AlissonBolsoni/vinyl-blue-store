package br.com.alissonbolsoni.bluestore.core.usecase

import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import java.util.*

interface OrderUseCase {

    @Throws(Exception::class)
    fun getOrderById(id: Int): Order

    @Throws(Exception::class)
    fun getOrderBetweenDates(start: Date, end: Date, pageable: LocalPageable?): LocalPage<Order>
}