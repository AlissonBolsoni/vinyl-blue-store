package br.com.alissonbolsoni.bluestore.core.usecase.repository

interface GenreRepository {
    fun existsGenre(genre: String): Boolean
}