package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.OrderResponse
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.mapper.toOrderResponse
import br.com.alissonbolsoni.bluestore.core.usecase.OrderUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.repository.OrderRepository
import java.util.*
import javax.inject.Named

@Named
class OrderUseCaseImpl(
    @Named private val orderRepository: OrderRepository
) : OrderUseCase {

    override fun getOrderById(id: Int): Order {
        return orderRepository.getOrderById(id)
    }

    override fun getOrderBetweenDates(start: Date, end: Date, pageable: LocalPageable?): LocalPage<Order> {
        return orderRepository.getOrderBetweenDates(start, end, pageable)
    }
}