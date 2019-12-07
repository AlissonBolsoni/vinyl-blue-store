package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.Cashback
import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.OrderResponse
import br.com.alissonbolsoni.bluestore.core.mapper.toOrderResponse
import br.com.alissonbolsoni.bluestore.core.usecase.NewOrderUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.repository.AlbumRepository
import br.com.alissonbolsoni.bluestore.core.usecase.repository.CashbackRepository
import br.com.alissonbolsoni.bluestore.core.usecase.repository.OrderRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named
import kotlin.collections.HashMap

@Named
class NewOrderUseCaseImpl(
    @Named private val albumRepository: AlbumRepository,
    @Named private val cashbackRepository: CashbackRepository,
    @Named private val orderRepository: OrderRepository
) : NewOrderUseCase {

    override fun registerNewOrder(albumsIds: List<Int>): OrderResponse {

        if (albumsIds.isEmpty()) throw IllegalArgumentException("A lista de albums não pode ser vazia")

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val cashbackByDayOfWeek = cashbackRepository.getCashbackByDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))

        val map = HashMap<Album, Cashback>()
        val albums = albumRepository.getAlbumByIds(albumsIds)
        if (albums.isEmpty()) throw Exception("Não foi possível encontrar os discos selecionados.")

        var total: Double = 0.0
        var cashback: Double = 0.0
        albums.forEach { album ->
            total += album.price
            val filter = cashbackByDayOfWeek.first { it.genre?.id == album.genre?.id }
            cashback += filter.value * album.price
            filter.price = filter.value * album.price
            map[album] = filter
        }

        val order = orderRepository.registerNewOrder(
            Order(
                total = total, cashback = cashback, date = sdf.parse(sdf.format(calendar.time)), albumsOrdered = map
            )
        )
        return order.toOrderResponse()
    }

}