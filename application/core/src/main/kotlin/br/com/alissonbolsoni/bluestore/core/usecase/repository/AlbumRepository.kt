package br.com.alissonbolsoni.bluestore.core.usecase.repository

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable

interface AlbumRepository {
    fun getAlbumById(id: Int):Album
    fun getAlbumsByGenre(genre: String, pageable: LocalPageable?): LocalPage<Album>
    fun getAllAlbums(pageable: LocalPageable?): LocalPage<Album>
    fun getAlbumByIds(ids: List<Int>): List<Album>
}
