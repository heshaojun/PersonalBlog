package cn.codejavahand.blog.service
/**
 * 网站访问统计服务接口
 */
interface IWebVisitsCountService {
    long getWebVisits()

    void visit()

    void storage()
}