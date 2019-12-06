package br.com.alissonbolsoni.bluestore.dataprovider.dao

import br.com.alissonbolsoni.bluestore.dataprovider.entity.AlbumTable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface AlbumDao : PagingAndSortingRepository<AlbumTable, Int> {

    fun findAllByAlbumGenreTableGenreNameIgnoreCaseOrderByAlbumName(name: String, pageable: Pageable): Page<AlbumTable>
    fun findAllByOrderByAlbumName(pageable: Pageable): Page<AlbumTable>
    fun findByAlbumIdIn(ids: List<Int>): List<AlbumTable>
}