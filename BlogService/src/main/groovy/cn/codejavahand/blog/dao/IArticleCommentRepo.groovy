package cn.codejavahand.blog.dao

import cn.codejavahand.blog.service.vo.CommentVo

interface IArticleCommentRepo {
    void addComment(String id, String commentId, CommentVo commentVo)

    List<CommentVo> getAllId(String id)

    void deleteComment(String id, String commentId)

    CommentVo getById(String id, String commentId)
}