package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description  TODO 存取更新文章原创标识
 */
interface IArticleOriginalMarkRepo {
    void createUpdate(String id, String mark)

    String getById(String id)
}