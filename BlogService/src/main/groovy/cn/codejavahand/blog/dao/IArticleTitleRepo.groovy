package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新标题
 */
interface IArticleTitleRepo {
    void updateCreate(String id, String title)

    String getById(String id)
}