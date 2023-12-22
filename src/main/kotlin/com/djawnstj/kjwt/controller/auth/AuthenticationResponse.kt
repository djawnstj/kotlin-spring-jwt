package com.djawnstj.kjwt.controller.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)
