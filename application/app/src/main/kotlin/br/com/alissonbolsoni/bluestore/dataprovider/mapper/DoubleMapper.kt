package br.com.alissonbolsoni.bluestore.dataprovider.mapper

import java.math.RoundingMode

fun Double.round(places: Int) = this.toBigDecimal().setScale(places, RoundingMode.CEILING).toDouble()