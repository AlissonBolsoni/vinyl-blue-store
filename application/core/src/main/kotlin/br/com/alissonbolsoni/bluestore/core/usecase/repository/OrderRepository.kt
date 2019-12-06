package br.com.alissonbolsoni.bluestore.core.usecase.repository

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.OrderResponse
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import java.util.*

interface OrderRepository {

    fun registerNewOrder(order: Order): Order

    fun getOrderById(id: Int): Order

    fun getOrderBetweenDates(start: Date, end: Date, pageable: LocalPageable?): LocalPage<Order>

}