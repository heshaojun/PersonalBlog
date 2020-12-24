package cn.codejavahand.blog.dao.repo

import cn.codejavahand.blog.dao.entity.ArticleInfoDo

interface IArticleInfoRepo {
    List<ArticleInfoDo> getAllArticleInfo()

    List<String> getAllId()
}