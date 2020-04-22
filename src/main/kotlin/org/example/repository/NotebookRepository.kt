package org.example.repository

import org.example.domain.Notebook
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NotebookRepository : JpaRepository<Notebook, UUID> {
    fun findByTitle(title: String): Notebook?
}