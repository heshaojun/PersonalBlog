package cn.codejavahand.blog.dao

import cn.codejavahand.blog.service.vo.CommentVo

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 文章评论数据存取操作接口
 */
interface IArticleCommentRepo {
    void addComment(String id, String commentId, CommentVo commentVo)

    List<CommentVo> getAllId(String id)

    void deleteComment(String id, String commentId)

    CommentVo getById(String id, String commentId)
}