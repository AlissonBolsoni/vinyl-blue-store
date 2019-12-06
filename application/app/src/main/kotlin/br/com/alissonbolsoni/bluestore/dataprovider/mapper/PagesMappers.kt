package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import br.com.alissonbolsoni.bluestore.core.entity.IsPageable
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.entrypoint.dto.IsPageableDto
import br.com.alissonbolsoni.bluestore.entrypoint.dto.IsPageableTable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest


fun LocalPageable?.toPageable() = PageRequest.of(this?.number ?: 0, this?.size ?: 20)

fun <T : IsPageable, R : IsPageableDto> LocalPage<T>.toPageDto(list: List<R>): Page<R?>? {
    val pageable = PageRequest.of(this.pageNumber, this.totalElements.toInt())
    return PageImpl<R>(list, pageable, this.totalElements)
}

fun <T : IsPageableTable, R : IsPageable> Page<T>.toLocalPage(list: List<R>): LocalPage<R> {
    return LocalPage(this.number, this.numberOfElements, this.totalPages, this.size.toLong(), list)
}
