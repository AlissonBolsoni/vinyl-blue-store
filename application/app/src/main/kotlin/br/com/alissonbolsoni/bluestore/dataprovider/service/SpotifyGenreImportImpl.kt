package br.com.alissonbolsoni.bluestore.dataprovider.service

import br.com.alissonbolsoni.bluestore.core.usecase.service.SpotifyGenreImport
import br.com.alissonbolsoni.bluestore.dataprovider.exceptions.EmptyGenresException
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toAlbum
import br.com.alissonbolsoni.bluestore.dataprovider.dao.AlbumDao
import br.com.alissonbolsoni.bluestore.dataprovider.dao.GenreDao
import br.com.alissonbolsoni.bluestore.dataprovider.repository.SpotifyRepository
import com.wrapper.spotify.exceptions.SpotifyWebApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException

@Service
open class SpotifyGenreImportImpl
@Autowired constructor(
    private val genreDao: GenreDao,
    private val albumDao: AlbumDao,
    private val spotifyRepository: SpotifyRepository
): SpotifyGenreImport {

    @Throws(EmptyGenresException::class)
    override fun syncDiscs() {
        runBlocking(Dispatchers.IO) {
            doImport()
        }
    }

    @Throws(EmptyGenresException::class)
    private fun doImport() {
        genreDao.findAll().forEach { genre ->
            try {
                val size = albumDao.findAllByAlbumGenreTableGenreName(genre.genreName).count()
                spotifyRepository.findTracks(genre.genreName, size)
                    .items.forEach {
                    albumDao.save(it.toAlbum(genre))
                }
            } catch (e: IOException) {
                throw e
            } catch (e: SpotifyWebApiException) {
                throw e
            }
        }
    }

}