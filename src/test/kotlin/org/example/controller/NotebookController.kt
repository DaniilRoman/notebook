package org.example.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotebookController {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

//    @Test
//    fun `Assert get all notebooks, content and status code`() {
//        val entity = restTemplate.getForEntity<String>("/api/v1/dsf")
//        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
//        assertThat(entity.body).contains("[All]")
//    }

    @Test
    fun contextLoads() {
    }

}


