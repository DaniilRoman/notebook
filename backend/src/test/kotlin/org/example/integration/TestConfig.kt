package org.example.integration

import com.opentable.db.postgres.embedded.EmbeddedPostgres
import org.example.NotebookApplication
import org.example.domain.Notebook
import org.example.domain.request.AccountRequest
import org.example.domain.response.TokenResponse
import org.example.domain.user.Account
import org.example.repository.AccountRepository
import org.example.utils.HEADER_PREFIX
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [NotebookApplication::class])
@TestPropertySource(locations = ["classpath:application-test.yml"])
@ContextConfiguration(classes = [TestContextConfiguration::class])
abstract class TestConfig {

    @Autowired
    lateinit var restTemplate: TestRestTemplate
    private lateinit var token: String

    @Before
    fun setup() {
        restTemplate.postForObject(
            "/api/v1/account/sign_up",
            AccountRequest("user","user"),
            Any::class.java
        )

        val tokenResponse = restTemplate.postForEntity(
            "/api/v1/account/login",
            AccountRequest("user","user"),
            TokenResponse::class.java
        )

        token = tokenResponse.body!!.token
    }

    protected fun <T, R> exchange(url: String, httpMethod: HttpMethod, body: T?, responseType: Class<R>)
            : ResponseEntity<R> {
        val headers = HttpHeaders()
        headers.set("Authorization", HEADER_PREFIX + token)

        return restTemplate.exchange(
            url,
            httpMethod,
            HttpEntity(body, headers),
            responseType
        )
    }

    @Test
    fun contextLoads() {
    }
}

@Configuration
class TestContextConfiguration {
    @Bean
    @Primary
    fun dataSource(): DataSource? {
        val embeddedPostgresBuilder = EmbeddedPostgres.builder().setPort(9999)
        return embeddedPostgresBuilder.start().postgresDatabase
    }
}