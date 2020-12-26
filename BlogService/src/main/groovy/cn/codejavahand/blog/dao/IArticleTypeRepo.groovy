package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO TODO 存取文章类型数据
 */
interface IArticleTypeRepo {
    void updateCreate(String id, String type)

    String getById(String id)
}