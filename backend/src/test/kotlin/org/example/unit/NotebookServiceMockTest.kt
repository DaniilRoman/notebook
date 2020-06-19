package org.example.unit

import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.example.exception.NotFoundException
import org.example.repository.NotebookRepository
import org.example.service.NotebookService
import org.example.service.impl.NotebookServiceImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class NotebookServiceMockTest {
    lateinit var notebookService: NotebookService
    @Mock
    lateinit var repository: NotebookRepository

    @Before
    fun setup() {
        notebookService = NotebookServiceImpl(repository)
    }

    @Test
    fun testSaveAndGet() {
        val notebookService = Mockito.mock(NotebookService::class.java)

        val notebookId = UUID.randomUUID()
        val notebook = Notebook(notebookId, "title", "text")

        val request = NotebookRequest(notebook.title, notebook.text)

        Mockito.`when`(notebookService.save(request)).then { notebook }
        Mockito.`when`(notebookService.getById(notebookId)).then { notebook }

        assertEquals(notebook, notebookService.save(request))

        // verify
        assertEquals(notebook, notebookService.getById(notebookId), "doesn't match")
        Mockito.verify(notebookService).getById(notebookId)

    }

    @Test(expected = NotFoundException::class)
    fun testNotFoundException() {
        val id = UUID.randomUUID()
        Mockito.`when`(repository.findById(id))
            .then{ Optional.empty<Notebook>() }
        notebookService.getById(id)
    }

//    @Test
//    fun testCapture() {
//        val notebookService = mock(NotebookService::class.java)
//        val arg = ArgumentCaptor.forClass(NotebookRequest::class.java)
//
//        val request = NotebookRequest("test", "test")
//
//        notebookService.save(request)
//        Mockito.verify(notebookService).save(arg.capture())
//        assertEquals(request, arg.value);
//    }

}