package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 存取更新网站访问量数据
 */
interface IWebVisitsRepo {

    void updateCreate(long visit)

    long getWebVisits()
}