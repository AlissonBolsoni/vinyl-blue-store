package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.AlbumsUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.repository.AlbumRepository
import br.com.alissonbolsoni.bluestore.core.usecase.repository.GenreRepository
import java.lang.IllegalArgumentException
import javax.inject.Named

@Named
class AlbumUseCaseImpl(
    @Named private val albumRepository: AlbumRepository,
    @Named private val genreRepository: GenreRepository
) : AlbumsUseCase {

    @Throws(IllegalArgumentException::class)
    override fun getAlbumBy(id: Int): Album {
        if (!albumRepository.existsId(id)) throw IllegalArgumentException("Não existe um Album com esse Id")
        return albumRepository.getAlbumById(id)
    }

    @Throws(IllegalArgumentException::class)
    override fun getAlbumsByGenre(genre: String, pageable: LocalPageable?): LocalPage<Album> {
        if (!genreRepository.existsGenre(genre)) throw IllegalArgumentException("Não existe esse genero de músicas")
        return albumRepository.getAlbumsByGenre(genre, pageable)
    }

    override fun getAlbums(pageable: LocalPageable?): LocalPage<Album> {
        return albumRepository.getAllAlbums(pageable)
    }
}