package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取文章类型数据
 */
interface IArticleTypeRepo {
    void updateCreate(String id, String type)

    String getById(String id)
}