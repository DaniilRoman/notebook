package org.example.controller

import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.example.service.NotebookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1")
class NotebookController {

    @Autowired
    private lateinit var notebookService: NotebookService

    @GetMapping
    fun getAll() = notebookService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID) = notebookService.getById(id)

    @PostMapping
    fun save(@RequestBody notebook: NotebookRequest) = notebookService.save(notebook)

    @PutMapping
    fun update(@RequestBody notebook: Notebook) = notebookService.update(notebook)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) = notebookService.delete(id)

}