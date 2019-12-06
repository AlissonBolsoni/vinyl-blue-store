package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.Cashback
import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.OrderResponse
import br.com.alissonbolsoni.bluestore.core.mapper.toOrderResponse
import br.com.alissonbolsoni.bluestore.dataprovider.entity.AlbumOrderedTable
import br.com.alissonbolsoni.bluestore.dataprovider.entity.OrderTable
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderDto
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderResponseDto
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun List<OrderTable>.toEntity() =
    this.map { it.toEntity() }.toList()

fun List<AlbumOrderedTable>.toMap(): Map<Album, Cashback> =
    this.map { Pair(it.toEntity(), Cashback()) }.toMap()

fun OrderTable.toEntity() =
    Order(
        this.orderId ?: 0,
        this.orderTotal.round(2),
        this.orderCashback.round(2),
        this.dateOrder ?: Date(),
        this.albumsOrdered.toMap()
    )

fun List<AlbumOrderedTable>.toEntity(): ArrayList<Album> {
    val albums = ArrayList<Album>()
    this.forEach {
        albums.add(it.toEntity())
    }
    return albums
}

fun AlbumOrderedTable.toEntity(): Album{
    val album = Album(
        this.albumTable?.albumId ?: 0,
        this.albumTable?.albumName ?: "",
        this.albumTable?.albumPrice ?: 0.0
    )
    album.genre = this.albumTable?.albumGenreTable?.toEntity()
    return album
}

fun List<OrderResponse>.toResponseDto():List<OrderResponseDto> =
    this.map { it.toDto() }.toList()

fun OrderResponse.toDto() = OrderResponseDto(
    this.orderId,
    this.total,
    this.cashback
)

fun Order.toTable(): OrderTable{
    val order = OrderTable(
        orderTotal = this.total,
        orderCashback = this.cashback,
        dateOrder = this.date
    )
    order.addAllAlbumOrdered(this.albumsOrdered)

    return order
}

fun List<Order>.toDto():List<OrderDto> =
    this.map { it.toDto() }.toList()

fun Order.toDto(): OrderDto{
    val toList = this.albumsOrdered?.keys?.toList()?.toAlbumDTO()?:ArrayList()
    return OrderDto(
        id = this.id,
        total = this.total,
        cashback = this.cashback,
        date = this.date,
        albumsOrdered = toList
    )
}

