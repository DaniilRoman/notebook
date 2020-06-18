package org.example.service

import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class NotebookServiceTest {
    @Mock
    lateinit var notebookService: NotebookService

    @Test
    fun testSaveAndGet() {
        val notebookId = UUID.randomUUID()
        val notebook = Notebook(notebookId, "title", "text")

        val request = NotebookRequest(notebook.title, notebook.text)

        Mockito.`when`(notebookService.save(request)).then { notebook }
        Mockito.`when`(notebookService.getById(notebookId)).then { notebook }

        assertEquals(notebook, notebookService.save(request))
        assertEquals(notebook, notebookService.getById(notebookId), "doesn't match")
    }

}