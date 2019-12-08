package br.com.alissonbolsoni.bluestore.dataprovider.dao

import br.com.alissonbolsoni.bluestore.dataprovider.entity.AlbumOrderedTable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AlbumsOrederedDao : CrudRepository<AlbumOrderedTable, Int> {
}