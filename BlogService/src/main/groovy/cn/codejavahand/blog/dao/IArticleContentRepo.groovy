package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO  存取跟新文章内容
 */
interface IArticleContentRepo {
    void updateCreate(String id, String content)

    String getById(String id)

}