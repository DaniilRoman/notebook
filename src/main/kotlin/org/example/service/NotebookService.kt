package org.example.service

import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import java.util.*

interface NotebookService {
    fun getAll(): List<Notebook>

    fun getById(id: UUID): Notebook?

    fun save(notebook: NotebookRequest): Notebook

    fun delete(id: UUID)

    fun update(notebook: Notebook): Notebook
}