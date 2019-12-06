package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.AlbumsUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.repository.AlbumRepository
import javax.inject.Named

@Named
class AlbumUseCaseImpl(
    @Named private val albumRepository: AlbumRepository
) : AlbumsUseCase {

    override fun getAlbumBy(id: Int): Album {
        return albumRepository.getAlbumById(id)
    }

    override fun getAlbumsByGenre(genre: String, pageable: LocalPageable?): LocalPage<Album> {
        return albumRepository.getAlbumsByGenre(genre, pageable)
    }

    override fun getAlbums(pageable: LocalPageable?): LocalPage<Album> {
        return albumRepository.getAllAlbums(pageable)
    }
}