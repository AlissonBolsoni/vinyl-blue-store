package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.repository.OrderRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

internal class OrderUseCaseImplTest {
    private val idSuccess = 10
    private val idError = 0
    private val total: Double = 45.0
    private val cashback: Double = 4.5
    private val order = Order(idSuccess, total, cashback)

    private val orderRepository = mockk<OrderRepository>().also {
        every { it.existsId(any<Int>()) } answers {
            args[0] as Int != 0
        }
        every { it.getOrderById(any()) } returns order
        every { it.getOrderBetweenDates(any(), any(), any()) } returns
                LocalPage<Order>(1,1, arrayListOf(order))
    }

    private val useCase = OrderUseCaseImpl(orderRepository)

    @Test
    fun getOrderByIdWithSuccess() {
        val orderById = useCase.getOrderById(idSuccess)

        assertEquals(idSuccess, orderById.id)
        assertEquals(total, orderById.total)
        assertEquals(cashback, orderById.cashback)
    }

    @Test
    fun getOrderByIdWithError() {
        assertThrows(Exception::class.java) {
            useCase.getOrderById(idError)
        }
    }

    @Test
    fun getOrderBetweenDatesWithSuccess() {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val start = sdf.parse("01/12/2019")
        val end = sdf.parse("15/12/2019")

        val orders = useCase.getOrderBetweenDates(start, end, LocalPageable(0, 1))

        assertEquals(idSuccess, orders.elements.first().id)
        assertEquals(total, orders.elements.first().total)
        assertEquals(cashback, orders.elements.first().cashback)
        assertTrue(orders.elements.first().date.after(start) && orders.elements.first().date.before(end))
    }

    @Test
    fun getOrderBetweenDatesWithError() {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val start = sdf.parse("20/12/2019")
        val end = sdf.parse("15/12/2019")

        assertThrows(Exception::class.java){
            useCase.getOrderBetweenDates(start, end, LocalPageable(0, 1))
        }
    }
}