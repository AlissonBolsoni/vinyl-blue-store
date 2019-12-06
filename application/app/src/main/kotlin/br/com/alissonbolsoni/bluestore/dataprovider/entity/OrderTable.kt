package br.com.alissonbolsoni.bluestore.dataprovider.entity

import br.com.alissonbolsoni.bluestore.core.entity.Album
import br.com.alissonbolsoni.bluestore.core.entity.Cashback
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toAlbumOrderedTable
import br.com.alissonbolsoni.bluestore.entrypoint.dto.IsPageableTable
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "orders")
open class OrderTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    val orderId: Int? = null,

    @Column(name = "total", nullable = false)
    val orderTotal: Double = 0.0,

    @Column(name = "cashback", nullable = false)
    val orderCashback: Double = 0.0,

    @Column(name = "dateOrder", nullable = false)
    val dateOrder: Date? = null,

    @OneToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    var albumsOrdered: MutableList<AlbumOrderedTable> = ArrayList()

) : IsPageableTable {

    fun addAllAlbumOrdered(map: Map<Album, Cashback>?) {
        val albums = ArrayList<AlbumOrderedTable>()
        map?.forEach {
            val album = it.key.toAlbumOrderedTable()
            album.orderTable = this
            album.albumOrderedCashbackPercent = it.value.value
            album.albumOrderedCashbackPrice = it.value.price
            albums.add(album)
        }
        albumsOrdered = albums
    }
}