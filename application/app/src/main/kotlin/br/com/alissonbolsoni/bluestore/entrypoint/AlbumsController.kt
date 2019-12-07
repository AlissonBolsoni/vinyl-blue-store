package br.com.alissonbolsoni.bluestore.entrypoint

import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.core.usecase.AlbumsUseCase
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toAlbumDTO
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toDto
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toPageDto
import br.com.alissonbolsoni.bluestore.entrypoint.AlbumsController.Companion.PATH
import br.com.alissonbolsoni.bluestore.entrypoint.dto.AlbumDTO
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Named


@RestController
@RequestMapping(PATH)
class AlbumsController(
    @Named private val albumsUseCase: AlbumsUseCase
) {

    companion object {
        internal const val PATH = "/album"
        internal const val PATH_GET_BY_ID = "/{id}"
        internal const val PATH_BY_GENRE = "/genre/{genre}"
    }

    @GetMapping(value = [PATH_GET_BY_ID])
    fun findAlbumById(@PathVariable id: Int): ResponseEntity<AlbumDTO> {

        return try {
            ResponseEntity(albumsUseCase.getAlbumBy(id).toAlbumDTO(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(
                HttpStatus.NOT_FOUND
            )
        }
    }

    @GetMapping(value = [PATH_BY_GENRE])
    fun findAlbumsByGenre(@PathVariable genre: String?, page: Int?, size: Int?): ResponseEntity<Page<AlbumDTO?>?> {
        return try {
            val localPageable = LocalPageable(page?: 0, size?: 20)
            val albums = albumsUseCase.getAlbumsByGenre(genre ?: "", localPageable)
            ResponseEntity(albums.toPageDto(albums.elements.toAlbumDTO()), HttpStatus.OK)
        } catch (e: Exception) {
            val orderDto = PageImpl<AlbumDTO>(
                listOf(AlbumDTO().apply { message = e.localizedMessage }),
                PageRequest.of(0, 1),
                1
            )
            ResponseEntity(
                orderDto,
                HttpStatus.NOT_FOUND
            )
        }
    }

    @GetMapping
    fun findAlbums(page: Int?, size: Int?): Page<AlbumDTO?>? {
        val localPageable = LocalPageable(page?: 0, size?: 20)
        val albums = albumsUseCase.getAlbums(localPageable)
        return albums.toPageDto(albums.elements.toAlbumDTO())
    }

}