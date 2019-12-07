package br.com.alissonbolsoni.bluestore.dataprovider.repository

import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.repository.OrderRepository
import br.com.alissonbolsoni.bluestore.dataprovider.dao.OrderDao
import br.com.alissonbolsoni.bluestore.dataprovider.exceptions.NotExistsOrderException
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toEntity
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toLocalPage
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toPageable
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toTable
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.inject.Named

@Named
class OrderRepositoryImpl(
    @Autowired private val orderDao: OrderDao
) : OrderRepository {

    override fun registerNewOrder(order: Order): Order {
        return orderDao.save(order.toTable()).toEntity()
    }

    @Throws(NotExistsOrderException::class)
    override fun getOrderById(id: Int): Order {

        val findById = orderDao.findById(id)

        if (!findById.isPresent)
            throw NotExistsOrderException()

        return findById.get().toEntity()
    }

    override fun getOrderBetweenDates(start: Date, end: Date, pageable: LocalPageable?): LocalPage<Order> {
        val pageOrderTable = orderDao.findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqual(start, end, pageable.toPageable())
        return pageOrderTable.toLocalPage(pageOrderTable.content.toEntity())
    }

    override fun existsId(id: Int): Boolean {
        return orderDao.existsById(id)
    }

}