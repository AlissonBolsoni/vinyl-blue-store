package br.com.alissonbolsoni.bluestore.dataprovider.start.spotify

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
open class SpotifyGenreImport
@Autowired constructor(
    private val genreDao: GenreDao,
    private val albumDao: AlbumDao,
    private val spotifyRepository: SpotifyRepository
) {

    fun syncDiscs() {
        runBlocking(Dispatchers.IO) {
            if (albumDao.count() == 0L) {
                try {
                    doImport()
                    println(albumDao.findAll())
                } catch (e: EmptyGenresException) {
                    throw e
                }
            }
        }
    }

    @Throws(EmptyGenresException::class)
    private fun doImport() {
        genreDao.findAll().forEach { genre ->
            try {
                spotifyRepository.findTracks(genre.genreName)
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