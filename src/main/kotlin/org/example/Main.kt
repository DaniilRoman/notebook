package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class NotebookApplication

fun main(args: Array<String>) {
    runApplication<NotebookApplication>(*args)
}
