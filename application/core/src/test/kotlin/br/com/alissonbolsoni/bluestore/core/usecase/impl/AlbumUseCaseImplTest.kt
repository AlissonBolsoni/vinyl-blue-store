package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.repository.AlbumRepository
import br.com.alissonbolsoni.bluestore.core.usecase.repository.GenreRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

internal class AlbumUseCaseImplTest {
    private val idSuccess = 10
    private val idError = 0
    private val albumName = "Album Test"
    private val albumPrice = 20.0
    private val album = Album(idSuccess, albumName, albumPrice)
    private val genderSuccess = "pop"
    private val genderError = "funk"

    private val albumRepository = mockk<AlbumRepository>().also {
        every { it.existsId(any<Int>()) } answers {
            args[0] as Int != 0
        }
        every { it.getAlbumById(any()) } returns album
        every { it.getAllAlbums(any<LocalPageable>()) } returns
                LocalPage(1, 1, arrayListOf(album))
        every { it.getAlbumsByGenre(any<String>(), any<LocalPageable>()) } returns
                LocalPage(1, 1, arrayListOf(album))
    }

    private val genreRepository = mockk<GenreRepository>().also {
        every { it.existsGenre(any()) } answers {
            if (args[0] == genderError)
                throw IllegalArgumentException()
            else
                true
        }
    }

    private val useCase = AlbumUseCaseImpl(albumRepository, genreRepository)

    @Test
    fun testGetAlbumByIdWithSuccess() {
        val albumById = useCase.getAlbumBy(idSuccess)
        assertEquals(idSuccess, albumById.id)
        assertEquals(albumName, albumById.name)
        assertEquals(albumPrice, albumById.price)
    }

    @Test
    fun testGetAlbumByIdWithError() {
        assertThrows(Exception::class.java) {
            useCase.getAlbumBy(idError)
        }
    }

    @Test
    fun testGetAllAlbums() {
        val allAlbums = useCase.getAlbums(LocalPageable(0, 20))

        assertFalse(allAlbums.elements.isEmpty())
        assertEquals(idSuccess, allAlbums.elements.first().id)
        assertEquals(albumName, allAlbums.elements.first().name)
        assertEquals(albumPrice, allAlbums.elements.first().price)
    }


    @Test
    fun testGetAlbumsByGenreWithSuccess() {
        val allAlbums = useCase.getAlbumsByGenre(genderSuccess, LocalPageable(0, 20))

        assertFalse(allAlbums.elements.isEmpty())
        assertEquals(idSuccess, allAlbums.elements.first().id)
        assertEquals(albumName, allAlbums.elements.first().name)
        assertEquals(albumPrice, allAlbums.elements.first().price)
    }

    @Test
    fun testGetAlbumsByGenreWithGenreError() {
        assertThrows(IllegalArgumentException::class.java){
            useCase.getAlbumsByGenre(genderError, LocalPageable(0, 20))
        }
    }


}
