package br.com.alissonbolsoni.bluestore.dataprovider.entity

import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity(name = "cashback_by_genre")
open class CashbackGenreTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    val cashbackId: Int? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_genre")
    val cashbackGenreTable: GenreTable? = null,

    @Column(name = "dayOfWeek", nullable = false)
    val cashbackDayOfWeek: Int = 0,
    @Column(name = "cashback", nullable = false)
    val cashbackValue: Double = 0.0
)