package cn.codejavahand.blog.service

import cn.codejavahand.blog.service.vo.RestRespVo


/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 文章列表获取接口，提供按类区分】按时间阅读脸区分等塞选数据
 */
interface IArticleListService {
    RestRespVo getArticleList(int size, int page, String scope, String order, String mark)
}