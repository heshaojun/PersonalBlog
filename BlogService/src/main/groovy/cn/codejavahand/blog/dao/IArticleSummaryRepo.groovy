package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新文章摘要
 */
interface IArticleSummaryRepo {
    void updateCreate(String id, String summary)

    String getById(String id)
}