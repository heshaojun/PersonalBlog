package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO  存取更新文章标签
 */
interface IArticleLabsRepo {
    void updateCreate(String id, String labs)

    String getById(String id)
}