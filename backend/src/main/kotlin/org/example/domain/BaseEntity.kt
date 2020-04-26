package org.example.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@MappedSuperclass
open class BaseEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID? = null,
    @CreationTimestamp
    val created: Date? = null,
    @UpdateTimestamp
    val updated: Date? = null
)