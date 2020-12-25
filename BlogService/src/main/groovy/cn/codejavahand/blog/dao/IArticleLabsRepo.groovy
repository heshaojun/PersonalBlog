package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新文章标签
 */
interface IArticleLabsRepo {
    void updateCreate(String id, String labs)

    String getById(String id)
}