package cn.codejavahand.blog.service

import cn.codejavahand.blog.service.vo.RestRespVo


interface IArticleListService {
    RestRespVo getArticleList(int size, int page, String scope, String order, String key)
}