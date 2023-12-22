package com.djawnstj.kjwt.controller.article

import com.djawnstj.kjwt.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/article")
class ArticleController(
    private val articleService: ArticleService
) {

    @GetMapping
    fun listAll(): List<ArticleResponse> =
        articleService.findAll()
            .map { it.toResponse() }

}