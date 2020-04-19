package org.example.controller

import org.apache.logging.log4j.LogManager
import org.example.domain.Notebook
import org.example.domain.request.NotebookRequest
import org.example.domain.user.Account
import org.example.repository.AccountRepository
import org.example.service.NotebookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.logging.Logger

@RestController
@RequestMapping("/api/v1")
class NotebookController {

    var logger = LogManager.getLogger(NotebookController::class)

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

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @GetMapping("/test/account")
    fun getAllUsers(): List<Account> {
        logger.info("Get all accounts")
        return accountRepository.findAll()
    }

}