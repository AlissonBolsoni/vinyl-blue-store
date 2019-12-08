package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.usecase.service.SpotifyGenreImport
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SpotifyUseCaseImplTest {

    private val spotifyGenreImport = mockk<SpotifyGenreImport>().also {
        every { it.syncDiscs() } returns Unit
    }

    @Test
    fun syncDiscs() {

        val useCase = SpotifyUseCaseImpl(spotifyGenreImport)

        useCase.syncDiscs()

        verify {
            spotifyGenreImport.syncDiscs()
        }

    }
}