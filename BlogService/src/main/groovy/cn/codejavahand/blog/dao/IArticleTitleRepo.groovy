package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO  存取更新标题
 */
interface IArticleTitleRepo {
    void updateCreate(String id, String title)

    String getById(String id)
}