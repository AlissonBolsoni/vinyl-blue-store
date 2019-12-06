package br.com.alissonbolsoni.bluestore.core.usecase

import br.com.alissonbolsoni.bluestore.core.entity.Album

interface FindAllAlbumsUseCase {

    fun findAllAlbums(): List<Album>

}