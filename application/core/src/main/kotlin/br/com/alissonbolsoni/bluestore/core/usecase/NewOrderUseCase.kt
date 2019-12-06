package br.com.alissonbolsoni.bluestore.core.usecase

import br.com.alissonbolsoni.bluestore.core.entity.OrderResponse

interface NewOrderUseCase {

    fun registerNewOrder(albumsIds: List<Int>): OrderResponse

}