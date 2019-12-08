package br.com.alissonbolsoni.bluestore.dataprovider.dao

import br.com.alissonbolsoni.bluestore.dataprovider.entity.OrderTable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface OrderDao: PagingAndSortingRepository<OrderTable, Int> {

    fun findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqual(start: Date, end: Date, pageable: Pageable): Page<OrderTable>
}