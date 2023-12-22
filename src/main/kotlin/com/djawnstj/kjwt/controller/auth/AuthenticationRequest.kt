package com.djawnstj.kjwt.controller.auth

data class AuthenticationRequest(
    val email: String,
    val password: String,
)
