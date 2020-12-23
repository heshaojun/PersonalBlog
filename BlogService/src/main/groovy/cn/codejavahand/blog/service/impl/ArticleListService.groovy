package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.service.IArticleListService
import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.stereotype.Service

@Service
class ArticleListService implements IArticleListService{
    @Override
    RestRespVo getArticleList(int size, int page, String scope, String order, String key) {
        return null
    }
}
