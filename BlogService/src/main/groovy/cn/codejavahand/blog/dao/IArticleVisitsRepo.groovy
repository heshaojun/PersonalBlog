package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新文章访问次数
 */
interface IArticleVisitsRepo {
    void updateCreate(String id, int visits)

    int getById(String id)
}