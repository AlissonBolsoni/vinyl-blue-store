package br.com.alissonbolsoni.bluestore.dataprovider.entity

import javax.persistence.*

@Entity
@Table(name = "album_order")
open class AlbumOrderedTable (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    val albumOrderedId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "id_order")
    var orderTable: OrderTable? = null,

    @ManyToOne
    @JoinColumn(name = "id_album")
    val albumTable: AlbumTable? = null,

    @Column(name = "Price", nullable = false)
    val albumOrderedPrice: Double = 0.0,

    @Column(name = "CashbackPrice", nullable = false)
    var albumOrderedCashbackPrice: Double = 0.0,

    @Column(name = "CashbackPercent", nullable = false)
    var albumOrderedCashbackPercent: Double = 0.0
)