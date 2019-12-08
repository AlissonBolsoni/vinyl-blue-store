package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.usecase.SpotifyUseCase
import br.com.alissonbolsoni.bluestore.core.usecase.service.SpotifyGenreImport
import javax.inject.Named

@Named
class SpotifyUseCaseImpl(
    private val spotifyGenreImport: SpotifyGenreImport
): SpotifyUseCase {

    override fun syncDiscs() {
        spotifyGenreImport.syncDiscs()
    }

}