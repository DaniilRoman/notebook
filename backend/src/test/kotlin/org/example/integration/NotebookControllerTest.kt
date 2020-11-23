package org.example.integration

import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.junit.Test
import org.springframework.http.HttpMethod
import kotlin.test.assertEquals


class NotebookControllerTest: TestConfig() {

    @Test
    fun `Assert save notebook and get one after that`() {

        val notebookAfterSave = exchange("/api/v1/notebook",
            HttpMethod.POST, NotebookRequest("1","1"), Notebook::class.java
        ).body!!

        val notebookAfterGet = exchange("/api/v1/notebook/${notebookAfterSave.id}",
            HttpMethod.GET, null, Notebook::class.java
        ).body!!

        assertEquals(notebookAfterSave, notebookAfterGet)
    }

}