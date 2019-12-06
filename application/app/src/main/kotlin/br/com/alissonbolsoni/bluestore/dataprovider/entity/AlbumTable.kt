package br.com.alissonbolsoni.bluestore.dataprovider.entity

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.entrypoint.dto.IsPageableTable
import com.wrapper.spotify.model_objects.specification.Track
import java.math.RoundingMode
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "album")
open class AlbumTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    val albumId: Int? = null,
    @Column(name = "name", nullable = false)
    val albumName: String = "",
    @Column(name = "price", nullable = false)
    val albumPrice: Double = 0.0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    val albumGenreTable: GenreTable? = null
): IsPageableTable {
    companion object {
        fun getRandomPrice(): Double {
            val value = (Random(System.nanoTime()).nextDouble() * (75 - 5) + 5).toBigDecimal()
            return value.setScale(2, RoundingMode.CEILING).toDouble()
        }
    }
}
