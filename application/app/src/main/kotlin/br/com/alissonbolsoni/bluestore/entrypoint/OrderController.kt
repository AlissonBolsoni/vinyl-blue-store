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
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
        if (orderRequestDto.albums.isEmpty())
            return ResponseEntity(
                OrderResponseDto(message = "A lista de albums não pode ser vazia"),
                HttpStatus.NOT_ACCEPTABLE
            )

        val registerNewOrder = newOrderUseCase.registerNewOrder(orderRequestDto.albums)
        return ResponseEntity(registerNewOrder.toDto(), HttpStatus.OK)
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
        page: Int?, size: Int?): Page<OrderDto?>? {
        if(start.after(end)) throw IllegalArgumentException("Data de início maior que a data final")

        val localPageable = LocalPageable(page?: 0, size?: 20)
        val order = orderUseCase.getOrderBetweenDates(start, end, localPageable)
        return order.toPageDto(order.elements.toDto())
    }

}