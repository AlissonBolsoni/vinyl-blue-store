package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import br.com.alissonbolsoni.bluestore.core.entity.*
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPage
import br.com.alissonbolsoni.bluestore.core.entity.vo.LocalPageable
import br.com.alissonbolsoni.bluestore.dataprovider.entity.*
import br.com.alissonbolsoni.bluestore.entrypoint.dto.AlbumDTO
import br.com.alissonbolsoni.bluestore.entrypoint.dto.IsPageableDto
import br.com.alissonbolsoni.bluestore.entrypoint.dto.IsPageableTable
import br.com.alissonbolsoni.bluestore.entrypoint.dto.OrderResponseDto
import com.wrapper.spotify.model_objects.specification.Track
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import kotlin.collections.ArrayList

fun GenreTable.toEntity() = Genre(
    this.genreId ?: 0, this.genreName
)

fun Genre.toTable() = GenreTable(
    this.id, this.name
)