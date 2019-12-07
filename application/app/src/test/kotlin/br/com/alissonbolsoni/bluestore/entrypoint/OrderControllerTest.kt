package br.com.alissonbolsoni.bluestore.entrypoint

import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.OrderResponse
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.NewOrderUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.OrderUseCase
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderRequestDto
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.http.HttpStatus
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named

internal class OrderControllerTest {
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val order = Order()

    private val newOrderUseCase = mockk<NewOrderUseCase>()
    private val orderUseCase = mockk<OrderUseCase>()
    private val controller = OrderController(newOrderUseCase, orderUseCase)

    @Test
    fun getOrderByIdWithSuccess() {
        every { orderUseCase.getOrderById(any()) } returns order
        val orderById = controller.getOrderById(1)
        assertEquals(HttpStatus.OK, orderById.statusCode)
    }

    @Test
    fun getOrderByIdWithError() {
        every { orderUseCase.getOrderById(any()) } throws Exception()
        val orderById = controller.getOrderById(1)

        assertEquals(HttpStatus.NOT_FOUND, orderById.statusCode)
    }

    @Test
    fun getOrderBetweenDatesWithSuccess() {
        every { orderUseCase.getOrderBetweenDates(any(), any(), any()) } returns
                LocalPage<Order>(0,1, listOf(order))
        val orderBetweenDates = controller.getOrderBetweenDates(
            sdf.parse("01/12/2019"),
            sdf.parse("01/12/2019"),
            0,20
        )

        assertEquals(HttpStatus.OK, orderBetweenDates.statusCode)
    }

    @Test
    fun getOrderBetweenDatesWithError() {
        every { orderUseCase.getOrderBetweenDates(any(), any(), any()) } throws Exception("Message")
        val orderBetweenDates = controller.getOrderBetweenDates(
            sdf.parse("01/12/2019"),
            sdf.parse("01/12/2019"),
            0,20
        )

        assertEquals(HttpStatus.NOT_ACCEPTABLE, orderBetweenDates.statusCode)
    }

    @Test
    fun registerOrderWithSuccess() {
        every { newOrderUseCase.registerNewOrder(any()) } returns
                OrderResponse(1, 10.0, 1.0)

        val registerOrder = controller.registerOrder(OrderRequestDto(listOf(1)))
        assertEquals(HttpStatus.OK, registerOrder.statusCode)
    }

    @Test
    fun registerOrderWithError() {
        every { newOrderUseCase.registerNewOrder(any()) } throws Exception("Message")

        val registerOrder = controller.registerOrder(OrderRequestDto(listOf(1)))
        assertEquals(HttpStatus.NOT_ACCEPTABLE, registerOrder.statusCode)
    }
}