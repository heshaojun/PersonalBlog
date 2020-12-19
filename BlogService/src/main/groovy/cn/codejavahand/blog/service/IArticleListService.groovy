package cn.codejavahand.blog.service

import cn.codejavahand.blog.dao.vo.ArticleListVo
import cn.codejavahand.blog.dao.vo.RestRespVo

interface IArticleListService {
    RestRespVo getArticleList(int size, int page, String scope, String order, String key)
}