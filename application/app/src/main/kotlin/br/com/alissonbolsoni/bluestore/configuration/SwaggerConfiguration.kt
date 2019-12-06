package br.com.alissonbolsoni.bluestore.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType.SWAGGER_2

@Configuration
@EnableSwagger2
open class SwaggerConfiguration {

    companion object {
        private const val EMAIL = "contato@alissonbolsoni.com.br"
        private const val SITE = "https://www.alissonbolsoni.com.br"
        private const val TITLE = "Alisson Bolsoni"
        private const val TITLE_LICENSE = "GC License"
        private const val URL_TERMS_OF_SERVICE = "Terms of Service"
        private const val LICENCE_URL =SITE
        private const val BASE_PACKAGE = "br.com.alissonbolsoni.bluestore.entrypoint"
    }

    @Value("\${application.version}")
    private lateinit var version: String

    @Value("\${application.name}")
    private lateinit var name: String

    @Value("\${application.description}")
    private lateinit var description: String

    @Bean
    open fun api(): Docket {
        return Docket(SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo())
    }

    private fun getApiInfo(): ApiInfo {
        return ApiInfo(
            name, description, version,
            URL_TERMS_OF_SERVICE,
            Contact(
                TITLE,
                SITE,
                EMAIL
            ),
            TITLE_LICENSE,
            LICENCE_URL,
            emptyList()
        )
    }

}
