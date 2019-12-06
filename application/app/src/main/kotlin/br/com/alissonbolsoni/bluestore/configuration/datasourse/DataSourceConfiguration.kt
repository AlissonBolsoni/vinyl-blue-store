package br.com.alissonbolsoni.bluestore.configuration.datasourse

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource("classpath:application.yml")
open class DataSourceConfiguration {

    @Value(
        "\${spring.datasource.url}"
    )
    private lateinit var dataSourceUrl: String

    @Value(
        "\${spring.datasource.username}"
    )
    private lateinit var dataSourceUserName: String

    @Value(
        "\${spring.datasource.password}"
    )
    private lateinit var dataSourcePassword: String

    @Value(
        "\${spring.datasource.driver-class-name}"
    )
    private lateinit var dataSourceDriver: String

    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(dataSourceDriver)
        dataSource.url = dataSourceUrl
        dataSource.username = dataSourceUserName
        dataSource.password = dataSourcePassword
        return dataSource
    }

}