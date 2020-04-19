package org.example.service.impl

import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.example.repository.NotebookRepository
import org.example.service.NotebookService
import org.example.utils.unwrap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotebookServiceImpl: NotebookService {

    @Autowired
    private lateinit var repository: NotebookRepository

    override fun getAll(): List<Notebook> {
        return repository.findAll()
    }

    override fun getById(id: UUID): Notebook? {
        return repository.findById(id).unwrap()
    }

    override fun save(notebook: NotebookRequest): Notebook {
        return repository.save(
            Notebook(notebook.title, notebook.text))
    }

    override fun delete(id: UUID) {
        repository.deleteById(id)
    }

    override fun update(notebook: Notebook): Notebook {
        return repository.save(notebook)
    }

}