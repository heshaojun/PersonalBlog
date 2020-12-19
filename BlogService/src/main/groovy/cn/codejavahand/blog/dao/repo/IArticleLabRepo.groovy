package cn.codejavahand.blog.dao.repo

import cn.codejavahand.blog.dao.entity.ArticleLabDo

interface IArticleLabRepo {
    ArticleLabDo getById(String id)

    void updateLabs(String id, ArticleLabDo articleLabDo)
}