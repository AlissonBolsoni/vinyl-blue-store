package br.com.alissonbolsoni.bluestore.core.usecase

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable

interface AlbumsUseCase {

    fun getAlbumBy(id: Int): Album

    fun getAlbumsByGenre(genre: String, pageable: LocalPageable?): LocalPage<Album>

    fun getAlbums(pageable: LocalPageable?): LocalPage<Album>
}