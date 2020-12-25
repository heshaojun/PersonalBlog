package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新文章状态数据
 */
interface IArticleStatusRepo {
    void updateCreate(String id, String status)

    String getById(String id)
}