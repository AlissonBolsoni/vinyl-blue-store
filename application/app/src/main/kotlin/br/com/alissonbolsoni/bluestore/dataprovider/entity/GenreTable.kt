package br.com.alissonbolsoni.bluestore.dataprovider.entity

import javax.persistence.*

@Entity
@Table(name = "genres")
data class GenreTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    val genreId: Int? = null,
    @Column(name = "name", nullable = false)
    val genreName: String = ""
)