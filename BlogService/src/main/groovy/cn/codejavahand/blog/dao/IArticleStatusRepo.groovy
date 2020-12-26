package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 存取更新文章状态数据
 */
interface IArticleStatusRepo {
    void updateCreate(String id, String status)

    String getById(String id)
}