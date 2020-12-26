package cn.codejavahand.blog.service

import cn.codejavahand.blog.service.vo.RestRespVo

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 网站统计信息接口，获取网站等统计信息
 */
interface IWebStatisticService {
    RestRespVo statistic()
}