package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO  存取更新文章摘要
 */
interface IArticleSummaryRepo {
    void updateCreate(String id, String summary)

    String getById(String id)
}