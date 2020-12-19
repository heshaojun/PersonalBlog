package cn.codejavahand.blog.dao.repo

import cn.codejavahand.blog.dao.entity.ArticleCommentDo

/**
 * 文章评论数据操作类
 */
interface IArticleCommentRepo {
    ArticleCommentDo getById(String id, String commentId)

    void addOneComment(String id, String commentId, ArticleCommentDo articleCommentDo)

    void removeOneComment(String id, String commentId)
}
