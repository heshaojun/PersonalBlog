package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取跟新文章内容
 */
interface IArticleContentRepo {
    void updateCreate(String id, String content)

    String getById(String id)
}