package cn.codejavahand.blog.dao.repo

import cn.codejavahand.blog.dao.entity.ArticleCommentDo

/**
 * 文章评论数据操作类
 */
interface IArticleCommentRepo {
    List<ArticleCommentDo> getCommentsById(String id)

    void addOneComment(String id, ArticleCommentDo articleCommentDo)

    void removeOneComment(String id, String commentId)

    List<String> countId(String id)
}
