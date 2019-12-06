package br.com.alissonbolsoni.bluestore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["br.com.alissonbolsoni.bluestore"])
@ComponentScan(basePackages = ["br.com.alissonbolsoni.bluestore", "br.com.alissonbolsoni.bluestore.configuration"])
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
