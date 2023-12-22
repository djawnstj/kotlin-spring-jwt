package com.djawnstj.kjwt.model

import com.djawnstj.kjwt.controller.article.ArticleResponse
import java.util.UUID

data class Article(
    val id: UUID,
    val title: String,
    val content: String,
) {

    fun toResponse(): ArticleResponse = ArticleResponse(id, title, content)

}
