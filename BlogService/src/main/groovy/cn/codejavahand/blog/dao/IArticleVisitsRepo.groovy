package cn.codejavahand.blog.dao
/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO TODO 存取更新文章访问次数
 */
interface IArticleVisitsRepo {
    void updateCreate(String id, int visits)

    int getById(String id)
}