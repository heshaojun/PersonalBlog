package cn.codejavahand.blog.dao.entity
/**
 * 文章评论实体
 */
class ArticleCommentDo {
    String id //文章id
    String commentId//
    String userName//用户名称
    String commentMsg//评论信息
    ArticleCommentDo() {
    }

    ArticleCommentDo(String id, String commentId, String userName, String commentMsg) {
        this.id = id
        this.commentId = commentId
        this.userName = userName
        this.commentMsg = commentMsg
    }
}
