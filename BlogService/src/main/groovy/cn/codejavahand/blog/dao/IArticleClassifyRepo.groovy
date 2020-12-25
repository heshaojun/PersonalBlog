package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新文章类别标签
 */
interface IArticleClassifyRepo {
    void updateCreate(String id, String classifyLabs)

    String getById(String id)
}