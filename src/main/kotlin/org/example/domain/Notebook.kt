package org.example.domain

import java.util.*
import javax.persistence.*

@Entity
data class Notebook(
    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID?,
    val title: String,
    val text: String
) {
    constructor(title: String, text: String): this(null, title, text)
}