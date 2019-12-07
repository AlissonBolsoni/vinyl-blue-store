package br.com.alissonbolsoni.bluestore.dataprovider.repository

import br.com.alissonbolsoni.bluestore.core.usecase.repository.GenreRepository
import br.com.alissonbolsoni.bluestore.dataprovider.dao.GenreDao
import org.springframework.beans.factory.annotation.Autowired
import javax.inject.Named

@Named
class GenreRepositoryImpl(
    @Autowired private val genreDao: GenreDao
): GenreRepository {

    override fun existsGenre(genre: String): Boolean {
        val genreTable = genreDao.findByGenreNameIgnoreCase(genre)

        return genreTable != null
    }
}