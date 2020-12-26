package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.dao.IArticleCountRepo
import cn.codejavahand.blog.dao.IArticleVisitsRepo
import cn.codejavahand.blog.dao.IWebVisitsRepo
import cn.codejavahand.blog.service.IArticleListService
import cn.codejavahand.blog.service.vo.CommentVo
import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleListService implements IArticleListService {
    @Autowired
    private IArticleCountRepo articleCountRepo
    @Autowired
    private IArticleVisitsRepo articleVisitsRepo

    @Override
    RestRespVo getArticleList(int size, int page, String scope, String order, String key) {
        RestRespVo respVo = new RestRespVo()
        respVo.setCode(200)
        respVo.setResult("success")
        respVo
    }

    void hold() {
        //获取所有上线的笔记id
        List<String> noteIdLis = articleCountRepo.countNote(CommonConst.ARTICLE_STATUS_ONLINE)
        //获取所有上线的博客id
        List<String> blogIdList = articleCountRepo.countBlog(CommonConst.ARTICLE_STATUS_ONLINE)
        noteIdLis.sort()
        articleVisitsRepo

    }
}
