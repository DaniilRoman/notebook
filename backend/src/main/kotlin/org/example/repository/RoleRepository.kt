package org.example.repository

import org.example.domain.user.Role
import org.example.domain.user.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, UUID> {
    fun getByName(name: UserRole): Role
}