package com.djawnstj.kjwt.repository

import com.djawnstj.kjwt.model.Role
import com.djawnstj.kjwt.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository(
    private val encoder: PasswordEncoder
) {

    private val users = mutableListOf(
        User(
            id = UUID.randomUUID(),
            email = "email-1@gmial.com",
            password = encoder.encode("pass1"),
            role = Role.USER
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-2@gmial.com",
            password = encoder.encode("pass2"),
            role = Role.ADMIN
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-3@gmial.com",
            password = encoder.encode("pass3"),
            role = Role.USER
        ),
    )

    fun save(user: User): Boolean =
        users.add(user.copy(password = encoder.encode(user.password)))


    fun findByEmail(email: String): User? = users.find { it.email == email }

    fun findAll(): List<User> = users

    fun findByUUID(uuid: UUID): User? = users.find { it.id == uuid }

    fun deleteByUUID(uuid: UUID): Boolean =
        users.removeIf { it.id == uuid }

}