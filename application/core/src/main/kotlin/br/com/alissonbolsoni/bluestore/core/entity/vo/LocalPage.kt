package br.com.alissonbolsoni.bluestore.core.entity.vo

import br.com.alissonbolsoni.bluestore.core.entity.IsPageable

class LocalPage<T: IsPageable> (
    val pageNumber: Int,
    val itemsForPage: Int,
    val numberOfPage: Int,
    val totalElements: Long,
    val elements: List<T>
)