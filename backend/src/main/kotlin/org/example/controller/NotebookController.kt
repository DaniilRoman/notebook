package org.example.controller

import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.example.service.NotebookService
import org.example.utils.API_NOTEBOOK_PREFIX
import org.example.utils.ID_PATH
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(API_NOTEBOOK_PREFIX)
class NotebookController(
    private val notebookService: NotebookService
) {
    @GetMapping
    fun getAll() = notebookService.getAll()

    @GetMapping(ID_PATH)
    fun getById(@PathVariable id: UUID) = notebookService.getById(id)

    @PostMapping
    fun save(@RequestBody notebook: NotebookRequest) = notebookService.save(notebook)

    @PutMapping
    fun update(@RequestBody notebook: Notebook) = notebookService.update(notebook)

    @DeleteMapping(ID_PATH)
    fun delete(@PathVariable id: UUID) = notebookService.delete(id)
}