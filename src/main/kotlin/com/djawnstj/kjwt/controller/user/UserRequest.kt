package com.djawnstj.kjwt.controller.user

import com.djawnstj.kjwt.model.Role
import com.djawnstj.kjwt.model.User
import java.util.*

data class UserRequest(
    val email: String,
    val password: String,
) {
    fun toModel(): User = User(
        id = UUID.randomUUID(),
        email = email,
        password = password,
        role = Role.USER
    )
}
