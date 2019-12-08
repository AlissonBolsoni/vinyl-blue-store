package br.com.alissonbolsoni.bluestore.dataprovider.repository

import br.com.alissonbolsoni.bluestore.configuration.spotify.SpotifyApiConnector
import br.com.alissonbolsoni.bluestore.dataprovider.exceptions.SpotifyImportException
import org.springframework.stereotype.Repository
import com.wrapper.spotify.exceptions.SpotifyWebApiException
import java.io.IOException
import com.wrapper.spotify.model_objects.specification.Paging
import com.wrapper.spotify.model_objects.specification.Track

@Repository
open class SpotifyRepository {

    @Throws(IOException::class, SpotifyWebApiException::class)
    fun findTracks(genre: String, size: Int): Paging<Track>{
        val spotifyApiConnector = SpotifyApiConnector()
        return spotifyApiConnector.findTrackByGenre(genre, size) ?: throw SpotifyImportException()
    }

}