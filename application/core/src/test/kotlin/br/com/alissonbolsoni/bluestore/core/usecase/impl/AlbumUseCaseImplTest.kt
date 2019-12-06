package br.com.alissonbolsoni.bluestore.core.usecase.impl

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.repository.AlbumRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AlbumUseCaseImplTest {
    private val idSuccess = 10
    private val idError = 0
    private val albumName = "Album Test"
    private val albumPrice = 20.0
    private val album = Album(idSuccess, albumName, albumPrice)
    private val genderSuccess = "pop"
    private val genderError = "funk"

    private val albumRepository = mockk<AlbumRepository>().also {
        every { it.getAlbumById(any()) } answers {
            if (args[0] == idSuccess)
                album
            else
                throw Exception()
        }
        every { it.getAllAlbums(any<LocalPageable>()) } answers {
            if ((args[0] as LocalPageable).number > 0)
                LocalPage(0, 0, 0, 0, ArrayList())
            else
                LocalPage(1, 1, 1, 1, arrayListOf(album))
        }
        every { it.getAlbumsByGenre(any<String>(), any<LocalPageable>()) } answers {
            if ((args[1] as LocalPageable).number > 0 || args[0] == genderError)
                LocalPage(0, 0, 0, 0, ArrayList())
            else
                LocalPage(1, 1, 1, 1, arrayListOf(album))
        }
    }

    @Test
    fun testGetAlbumByIdWithSuccess() {
        val albumById = albumRepository.getAlbumById(idSuccess)
        assertEquals(idSuccess, albumById.id)
        assertEquals(albumName, albumById.name)
        assertEquals(albumPrice, albumById.price)
    }

    @Test
    fun testGetAlbumByIdWithError() {
        assertThrows(Exception::class.java) {
            albumRepository.getAlbumById(idError)
        }
    }

    @Test
    fun testGetAllAlbumsWithSuccess() {
        val allAlbums = albumRepository.getAllAlbums(LocalPageable(0, 20))

        assertFalse(allAlbums.elements.isEmpty())
        assertEquals(idSuccess, allAlbums.elements.first().id)
        assertEquals(albumName, allAlbums.elements.first().name)
        assertEquals(albumPrice, allAlbums.elements.first().price)
    }

    @Test
    fun testGetAllAlbumsWithError() {
        val allAlbums = albumRepository.getAllAlbums(LocalPageable(1, 20))
        assertTrue(allAlbums.elements.isEmpty())
    }


    @Test
    fun testGetAlbumsByGenreWithSuccess() {
        val allAlbums = albumRepository.getAlbumsByGenre(genderSuccess, LocalPageable(0, 20))

        assertFalse(allAlbums.elements.isEmpty())
        assertEquals(idSuccess, allAlbums.elements.first().id)
        assertEquals(albumName, allAlbums.elements.first().name)
        assertEquals(albumPrice, allAlbums.elements.first().price)
    }

    @Test
    fun testGetAlbumsByGenreWithGenreError() {
        val allAlbums = albumRepository.getAlbumsByGenre(genderError, LocalPageable(0, 20))
        assertTrue(allAlbums.elements.isEmpty())
    }

    @Test
    fun testGetAlbumsByGenreWithPageError() {
        val allAlbums = albumRepository.getAlbumsByGenre(genderSuccess, LocalPageable(1, 20))
        assertTrue(allAlbums.elements.isEmpty())
    }

}
