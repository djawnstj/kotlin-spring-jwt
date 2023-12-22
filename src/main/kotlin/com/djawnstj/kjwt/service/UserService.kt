package com.djawnstj.kjwt.service

import com.djawnstj.kjwt.model.User
import com.djawnstj.kjwt.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(user: User): User? =
        userRepository.findByEmail(user.email)
            .let { null }
            ?: run {
                userRepository.save(user)
                user
            }

    fun findByUUID(uuid: UUID): User? =
        userRepository.findByUUID(uuid)

    fun findAll(): List<User> = userRepository.findAll()

    fun deleteByUUD(uuid: UUID): Boolean = userRepository.deleteByUUID(uuid)

}