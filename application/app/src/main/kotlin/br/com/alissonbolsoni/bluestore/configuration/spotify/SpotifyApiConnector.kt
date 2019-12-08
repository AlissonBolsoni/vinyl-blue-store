package br.com.alissonbolsoni.bluestore.configuration.spotify

import com.wrapper.spotify.SpotifyApi
import com.wrapper.spotify.exceptions.SpotifyWebApiException
import com.wrapper.spotify.model_objects.credentials.ClientCredentials
import com.wrapper.spotify.model_objects.specification.Paging
import com.wrapper.spotify.model_objects.specification.Track
import java.io.IOException

class SpotifyApiConnector {
    companion object {
        private const val CLIENT_ID = "2f032be536d6417a9ad797bbd7990aea"
        private const val CLIENT_SECRET = "6814b876939e444783e1b5d25e4b581a"
        private const val QUERY_BY_GENRE = "genre: %s"
    }

    private var spotifyApi: SpotifyApi
    private var clientCredentials: ClientCredentials

    init {
        spotifyApi = SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .build()

        clientCredentials = spotifyApi.clientCredentials().build().execute()
        spotifyApi.accessToken = clientCredentials.accessToken
    }

    @Throws(IOException::class, SpotifyWebApiException::class)
    fun findTrackByGenre(genre: String, size: Int): Paging<Track>? {
        val request = spotifyApi
            .searchTracks(String.format(QUERY_BY_GENRE, genre))
            .limit(50)
            .offset(size)
            .build()

        return request.execute()
    }

}