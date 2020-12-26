package cn.codejavahand.blog.service
/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 网站访问统计服务接口，提供网站访问数据等存取
 */
interface IWebVisitsCountService {
    long getWebVisits()

    void visit()

    void storage()
}