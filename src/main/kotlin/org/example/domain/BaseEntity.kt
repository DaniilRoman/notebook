package org.example.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity(
    @Id
    @GeneratedValue(generator = "UUID")
    val id: UUID?,
    @CreatedDate
    val created: Date?,
    @LastModifiedDate
    val updated: Date?
) {
    constructor(): this(null, null, null)
}