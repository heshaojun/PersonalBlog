package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO  存取更新网站访问量数据
 */
interface IWebVisitsRepo {

    void updateCreate(long visit)

    long getWebVisits()
}