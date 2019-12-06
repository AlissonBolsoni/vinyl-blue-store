package br.com.alissonbolsoni.bluestore.dataprovider.repository

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.repository.AlbumRepository
import br.com.alissonbolsoni.bluestore.dataprovider.exceptions.NotExistsAlbumException
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toEntity
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toLocalPage
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toPageable
import br.com.alissonbolsoni.bluestore.dataprovider.dao.AlbumDao
import org.springframework.beans.factory.annotation.Autowired
import javax.inject.Named

@Named
class AlbumRepositoryImpl(
    @Autowired private val albumDao: AlbumDao
) : AlbumRepository {

    @Throws(NotExistsAlbumException::class)
    override fun getAlbumById(id: Int): Album {
        val album = albumDao.findById(id)

        if (!album.isPresent)
            throw NotExistsAlbumException()

        return album.get().toEntity()
    }

    override fun getAllAlbums(pageable: LocalPageable?): LocalPage<Album> {
        val albums = albumDao.findAllByOrderByAlbumName(pageable.toPageable())
        return albums.toLocalPage(albums.content.toEntity())
    }

    override fun getAlbumsByGenre(genre: String, pageable: LocalPageable?): LocalPage<Album> {
        val albums =
            albumDao.findAllByAlbumGenreTableGenreNameIgnoreCaseOrderByAlbumName(genre, pageable.toPageable())
        return albums.toLocalPage(albums.content.toEntity())
    }

    override fun getAlbumByIds(ids: List<Int>): List<Album> {
        return albumDao.findByAlbumIdIn(ids).toEntity()
    }

}