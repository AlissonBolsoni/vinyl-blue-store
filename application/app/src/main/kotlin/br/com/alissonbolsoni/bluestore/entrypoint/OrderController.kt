package br.com.alissonbolsoni.bluestore.entrypoint

import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.NewOrderUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.OrderUseCase
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toDto
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toResponseDto
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toPageDto
import br.com.alissonbolsoni.bluestore.entrypoint.OrderController.Companion.PATH
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderDto
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderRequestDto
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.util.*
import javax.inject.Named

@RestController
@RequestMapping(PATH)
class OrderController(
    @Named private val newOrderUseCase: NewOrderUseCase,
    @Named private val orderUseCase: OrderUseCase
) {

    companion object {
        internal const val PATH = "/order"
        internal const val PATH_REGISTER = "/register"
        internal const val PATH_GET_BY_ID = "/{id}"
        internal const val PATH_GET_BY_DATES = "/date"
    }

    @PostMapping(value = [PATH_REGISTER])
    fun registerOrder(@RequestBody orderRequestDto: OrderRequestDto): ResponseEntity<OrderResponseDto> {
        return try {
            val registerNewOrder = newOrderUseCase.registerNewOrder(orderRequestDto.albums)
            ResponseEntity(registerNewOrder.toDto(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(
                OrderResponseDto(message = e.localizedMessage),
                HttpStatus.NOT_ACCEPTABLE
            )
        }
    }

    @GetMapping(value = [PATH_GET_BY_ID])
    fun getOrderById(@PathVariable id: Int): ResponseEntity<OrderDto> {
        return try {
            ResponseEntity(orderUseCase.getOrderById(id).toDto(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(
                HttpStatus.NOT_FOUND
            )
        }
    }

    @GetMapping(value = [PATH_GET_BY_DATES])
    fun getOrderBetweenDates(
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @RequestParam("start") start: Date,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        @RequestParam("end") end: Date,
        page: Int?, size: Int?
    ): ResponseEntity<Page<OrderDto?>?> {
        return try {
            val localPageable = LocalPageable(page ?: 0, size ?: 20)
            val order = orderUseCase.getOrderBetweenDates(start, end, localPageable)
            ResponseEntity(order.toPageDto(order.elements.toDto()), HttpStatus.OK)
        } catch (e: Exception) {
            val orderDto = PageImpl<OrderDto>(
                listOf(OrderDto().apply { message = e.localizedMessage }),
                PageRequest.of(0, 1),
                1
            )
            ResponseEntity(
                orderDto,
                HttpStatus.NOT_ACCEPTABLE
            )
        }

    }

}