package br.com.alissonbolsoni.bluestore.entrypoint

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.Order
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.usecase.AlbumsUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.http.HttpStatus

internal class AlbumsControllerTest {
    private val album = Album(1, "Album", 10.0)
    private val albumsUseCase = mockk<AlbumsUseCase>()
    private val controller = AlbumsController(albumsUseCase)

    @Test
    fun findAlbumById() {
        every { albumsUseCase.getAlbumBy(any()) } returns album
        val albumById = controller.findAlbumById(1)

        assertEquals(HttpStatus.OK, albumById.statusCode)
    }

    @Test
    fun findAlbumByIdWithError() {
        every { albumsUseCase.getAlbumBy(any()) } throws Exception()
        val albumById = controller.findAlbumById(1)

        assertEquals(HttpStatus.NOT_FOUND, albumById.statusCode)
    }

    @Test
    fun findAlbumsByGenre() {
        every { albumsUseCase.getAlbumsByGenre(any(), any()) } returns
                LocalPage<Album>(0,1, listOf(album))
        val albumByGenre = controller.findAlbumsByGenre("pop",0,20)

        assertEquals(HttpStatus.OK, albumByGenre.statusCode)
    }

    @Test
    fun findAlbumsByGenreWithError() {
        every { albumsUseCase.getAlbumsByGenre(any(), any()) } throws Exception("Message")
        val albumByGenre = controller.findAlbumsByGenre("pop",0,20)

        assertEquals(HttpStatus.NOT_FOUND, albumByGenre.statusCode)
    }

    @Test
    fun findAlbums() {
        every { albumsUseCase.getAlbums(any()) } returns
                LocalPage<Album>(0,1, listOf(album))

        val albums = controller.findAlbums(1, 10)
        assertNotNull(albums)
        assertNotNull(albums?.isEmpty)
        if (albums?.isEmpty != null)
            assertFalse(albums.isEmpty)
    }

}