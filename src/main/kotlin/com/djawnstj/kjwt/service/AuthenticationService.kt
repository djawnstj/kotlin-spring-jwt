package com.djawnstj.kjwt.service

import com.djawnstj.kjwt.controller.auth.AuthenticationRequest
import com.djawnstj.kjwt.controller.auth.AuthenticationResponse
import com.djawnstj.kjwt.config.JwtProperties
import com.djawnstj.kjwt.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)

        val now = System.currentTimeMillis()

        val accessToken = generateAccessToken(user, now)
        val refreshToken = generateRefreshToken(user, now)

        refreshTokenRepository.save(refreshToken, user)

        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    private fun generateRefreshToken(user: UserDetails, now: Long) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(now + jwtProperties.refreshTokenExpiration)
    )

    private fun generateAccessToken(user: UserDetails, now: Long) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(now + jwtProperties.accessTokenExpiration)
    )

    fun refreshAccessToken(token: String): String? {
        val extractedEmail = tokenService.extractEmail(token)

        return extractedEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(token)

            if (!tokenService.isExpired(token) && currentUserDetails.username == refreshTokenUserDetails?.username)
                generateAccessToken(currentUserDetails, System.currentTimeMillis())
            else null
        }
    }

}
