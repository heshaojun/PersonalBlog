package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 存取更新文章类别标签
 */
interface IArticleClassifyRepo {
    void updateCreate(String id, String classifyLabs)

    String getById(String id)
}