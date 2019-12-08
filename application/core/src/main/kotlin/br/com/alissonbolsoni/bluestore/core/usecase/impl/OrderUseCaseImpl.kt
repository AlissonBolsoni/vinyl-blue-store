package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.OrderUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.repository.OrderRepository
import java.util.*
import javax.inject.Named

@Named
class OrderUseCaseImpl(
    @Named private val orderRepository: OrderRepository
) : OrderUseCase {

    @Throws(Exception::class)
    override fun getOrderById(id: Int): Order {
        if (!orderRepository.existsId(id)) throw Exception("Não existe uma compra com esse Id")
        return orderRepository.getOrderById(id)
    }

    @Throws(Exception::class)
    override fun getOrderBetweenDates(start: Date, end: Date, pageable: LocalPageable?): LocalPage<Order> {
        if(start.after(end)) throw Exception("Data de início maior que a data final")

        return orderRepository.getOrderBetweenDates(start, end, pageable)
    }
}