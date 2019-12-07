package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.Cashback
import br.com.alissonbolsoni.bluestore.core.entity.Genre
import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.usecase.NewOrderUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.repository.AlbumRepository
import br.com.alissonbolsoni.bluestore.core.usecase.repository.CashbackRepository
import br.com.alissonbolsoni.bluestore.core.usecase.repository.OrderRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException
import java.util.*

internal class NewOrderUseCaseImplTest {

    private val idSuccess = 10
    private val albumName = "Album Test"
    private val albumPrice = 20.0
    private val idGenre = 1
    private val genreName = "POP"
    private val genre = Genre(idGenre, genreName)
    private val album = Album(idSuccess, albumName, albumPrice).also {
        it.genre = genre
    }

    private val cashback = Cashback(1, genre, 1, 0.1)
    private val order = Order(
        total = albumPrice, cashback = (cashback.value * albumPrice), date = Date()
    )

    private val albumRepository = mockk<AlbumRepository>().also {
        every { it.getAlbumByIds(any()) } returns listOf(album)
    }
    private val cashbackRepository = mockk<CashbackRepository>().also {
        every { it.getCashbackByDayOfWeek(any()) } returns listOf(cashback)
    }
    private val orderRepository = mockk<OrderRepository>().also {
        every { it.registerNewOrder(any()) } returns order
    }

    private val useCase = NewOrderUseCaseImpl(albumRepository, cashbackRepository, orderRepository)
    @Test
    fun registerNewOrderWithSuccess() {

        val newOrder = useCase.registerNewOrder(listOf(idSuccess))

        assertEquals(order.total, newOrder.total)
        assertEquals(order.cashback, newOrder.cashback)
    }

    @Test
    fun registerNewOrderWithEmptyList() {

        assertThrows(IllegalArgumentException::class.java){
            useCase.registerNewOrder(emptyList())
        }

    }

    @Test
    fun registerNewOrderWithNoAlbum() {
        every { albumRepository.getAlbumByIds(any()) } throws Exception()
        assertThrows(Exception::class.java){
            useCase.registerNewOrder(listOf(idSuccess))
        }

    }
}
