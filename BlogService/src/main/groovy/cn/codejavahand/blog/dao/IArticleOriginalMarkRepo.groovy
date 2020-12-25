package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新文章原创标识
 */
interface IArticleOriginalMarkRepo {
    void createUpdate(String id, String mark)

    String getById(String id)
}