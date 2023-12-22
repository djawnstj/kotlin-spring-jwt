package com.djawnstj.kjwt.service

import com.djawnstj.kjwt.model.Article
import com.djawnstj.kjwt.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {

    fun findAll(): List<Article> = articleRepository.findAll()

}