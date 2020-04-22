package org.example.service.impl

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.example.exception.NotFoundException
import org.example.repository.NotebookRepository
import org.example.service.NotebookService
import org.example.utils.unwrap
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotebookServiceImpl(
    private val repository: NotebookRepository
): NotebookService {

    companion object {
        val log: Logger = LogManager.getLogger(NotebookService::class.java)
    }

    override fun getAll(): List<Notebook> {
        return repository.findAll().apply {
            log.debug("Get all notebooks.")
        }
    }

    override fun getById(id: UUID): Notebook {
        return repository.findById(id).unwrap()
            ?: throw NotFoundException("Notebook with id: `$id` not found.")
    }

    override fun save(notebook: NotebookRequest): Notebook = with(notebook) {
        return repository.save(
            Notebook(title = title, text = text)).apply {
            log.info("New notebook: `$title` has been created.")
        }
    }

    override fun delete(id: UUID) {
        repository.deleteById(id)
    }

    override fun update(notebook: Notebook): Notebook {
        return repository.save(notebook).apply {
            log.info("New notebook: `$title` has been created.")
        }
    }

}