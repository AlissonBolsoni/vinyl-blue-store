package br.com.alissonbolsoni.bluestore.configuration

import br.com.alissonbolsoni.bluestore.dataprovider.entity.CashbackGenreTable
import br.com.alissonbolsoni.bluestore.dataprovider.entity.GenreTable
import br.com.alissonbolsoni.bluestore.dataprovider.dao.CashbackDao
import br.com.alissonbolsoni.bluestore.dataprovider.dao.GenreDao
import br.com.alissonbolsoni.bluestore.dataprovider.start.spotify.SpotifyGenreImport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@EnableScheduling
class Startup @Autowired constructor(
    private val genreDao: GenreDao,
    private val cashbackDao: CashbackDao,
    private val spotifyGenreImport: SpotifyGenreImport
) {

    @PostConstruct
    private fun prepareDatabase() {
        try {
            val allGenres = genreDao.findAll().toList()
            if (allGenres.isEmpty())
                createValuesToInsert()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        importSpotifyDiscs()
    }

    @Scheduled(cron = "0 0 0/1 1/1 * ?")
    private fun importSpotifyDiscs() {
        try {
            spotifyGenreImport.syncDiscs()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /*
    * Dias da semana
    * DOMINGO = 1
    * SEGUNDA = 2
    * TERÇA = 3
    * QUARTA = 4
    * QUINTA = 5
    * SEXTA = 6
    * SABADO = 7
    * */
    private fun createValuesToInsert() {
        val pop = genreDao.save(GenreTable(genreName = "POP"))
        cashbackDao.saveAll(
            mutableListOf(
                CashbackGenreTable(cashbackGenreTable = pop, cashbackDayOfWeek = 1, cashbackValue = 0.25),
                CashbackGenreTable(cashbackGenreTable = pop, cashbackDayOfWeek = 2, cashbackValue = 0.07),
                CashbackGenreTable(cashbackGenreTable = pop, cashbackDayOfWeek = 3, cashbackValue = 0.06),
                CashbackGenreTable(cashbackGenreTable = pop, cashbackDayOfWeek = 4, cashbackValue = 0.02),
                CashbackGenreTable(cashbackGenreTable = pop, cashbackDayOfWeek = 5, cashbackValue = 0.1),
                CashbackGenreTable(cashbackGenreTable = pop, cashbackDayOfWeek = 6, cashbackValue = 0.15),
                CashbackGenreTable(cashbackGenreTable = pop, cashbackDayOfWeek = 7, cashbackValue = 0.20)
            )
        )

        val mpb = genreDao.save(GenreTable(genreName = "MPB"))
        cashbackDao.saveAll(
            mutableListOf(
                CashbackGenreTable(cashbackGenreTable = mpb, cashbackDayOfWeek = 1, cashbackValue = 0.3),
                CashbackGenreTable(cashbackGenreTable = mpb, cashbackDayOfWeek = 2, cashbackValue = 0.05),
                CashbackGenreTable(cashbackGenreTable = mpb, cashbackDayOfWeek = 3, cashbackValue = 0.1),
                CashbackGenreTable(cashbackGenreTable = mpb, cashbackDayOfWeek = 4, cashbackValue = 0.15),
                CashbackGenreTable(cashbackGenreTable = mpb, cashbackDayOfWeek = 5, cashbackValue = 0.20),
                CashbackGenreTable(cashbackGenreTable = mpb, cashbackDayOfWeek = 6, cashbackValue = 0.25),
                CashbackGenreTable(cashbackGenreTable = mpb, cashbackDayOfWeek = 7, cashbackValue = 0.30)
            )
        )

        val classic = genreDao.save(GenreTable(genreName = "CLASSIC"))
        cashbackDao.saveAll(
            mutableListOf(
                CashbackGenreTable(cashbackGenreTable = classic, cashbackDayOfWeek = 1, cashbackValue = 0.35),
                CashbackGenreTable(cashbackGenreTable = classic, cashbackDayOfWeek = 2, cashbackValue = 0.03),
                CashbackGenreTable(cashbackGenreTable = classic, cashbackDayOfWeek = 3, cashbackValue = 0.5),
                CashbackGenreTable(cashbackGenreTable = classic, cashbackDayOfWeek = 4, cashbackValue = 0.08),
                CashbackGenreTable(cashbackGenreTable = classic, cashbackDayOfWeek = 5, cashbackValue = 0.13),
                CashbackGenreTable(cashbackGenreTable = classic, cashbackDayOfWeek = 6, cashbackValue = 0.18),
                CashbackGenreTable(cashbackGenreTable = classic, cashbackDayOfWeek = 7, cashbackValue = 0.25)
            )
        )

        val rock = genreDao.save(GenreTable(genreName = "ROCK"))
        cashbackDao.saveAll(
            mutableListOf(
                CashbackGenreTable(cashbackGenreTable = rock, cashbackDayOfWeek = 1, cashbackValue = 0.40),
                CashbackGenreTable(cashbackGenreTable = rock, cashbackDayOfWeek = 2, cashbackValue = 0.1),
                CashbackGenreTable(cashbackGenreTable = rock, cashbackDayOfWeek = 3, cashbackValue = 0.15),
                CashbackGenreTable(cashbackGenreTable = rock, cashbackDayOfWeek = 4, cashbackValue = 0.15),
                CashbackGenreTable(cashbackGenreTable = rock, cashbackDayOfWeek = 5, cashbackValue = 0.15),
                CashbackGenreTable(cashbackGenreTable = rock, cashbackDayOfWeek = 6, cashbackValue = 0.20),
                CashbackGenreTable(cashbackGenreTable = rock, cashbackDayOfWeek = 7, cashbackValue = 0.40)
            )
        )

    }


}