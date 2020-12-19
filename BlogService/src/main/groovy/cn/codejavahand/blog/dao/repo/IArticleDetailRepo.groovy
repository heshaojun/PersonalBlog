package cn.codejavahand.blog.dao.repo

import cn.codejavahand.blog.dao.entity.ArticleDetailDo

/**
 * 文章详情数据操作接口
 */
interface IArticleDetailRepo {
    //通过id查询文章
    ArticleDetailDo getById(String id)
    //更新文章
    void update(String id, ArticleDetailDo articleDetailDo)
    //添加id为了方便缓存
    void addArticle(String id, ArticleDetailDo articleDetailDo)
}