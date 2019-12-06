package br.com.alissonbolsoni.bluestore.dataprovider.repository

import br.com.alissonbolsoni.bluestore.core.entity.Cashback
import br.com.alissonbolsoni.bluestore.core.usecase.repository.CashbackRepository
import br.com.alissonbolsoni.bluestore.dataprovider.dao.CashbackDao
import br.com.alissonbolsoni.bluestore.dataprovider.mapper.toEntity
import org.springframework.beans.factory.annotation.Autowired
import javax.inject.Named

@Named
open class CashbackRepositoryImpl(
    @Autowired private val cashbackDao: CashbackDao
) : CashbackRepository {

    override fun getCashbackByDayOfWeek(dayOfWeek: Int): List<Cashback> {
        return cashbackDao.findByCashbackDayOfWeek(dayOfWeek).toEntity()
    }

}