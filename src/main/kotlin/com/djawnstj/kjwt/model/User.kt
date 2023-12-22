package com.djawnstj.kjwt.model

import com.djawnstj.kjwt.controller.user.UserResponse
import java.util.UUID

data class User(
    val id: UUID,
    val email: String,
    val password: String,
    val role: Role,
) {
    fun toResponse(): UserResponse = UserResponse(id, email)
}

enum class Role {
    USER, ADMIN
}
