package cn.codejavahand.blog.service.vo

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.dao.IArticleClassifyRepo
import cn.codejavahand.blog.dao.IArticleCommentRepo
import cn.codejavahand.blog.dao.IArticleContentRepo
import cn.codejavahand.blog.dao.IArticleLabsRepo
import cn.codejavahand.blog.dao.IArticleOriginalMarkRepo
import cn.codejavahand.blog.dao.IArticleStatusRepo
import cn.codejavahand.blog.dao.IArticleTitleRepo
import cn.codejavahand.blog.dao.IArticleTypeRepo
import cn.codejavahand.blog.dao.IArticleVisitsRepo
import cn.codejavahand.blog.service.IArticleDetailService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.lang.reflect.Array
import java.text.SimpleDateFormat

/**
 * @author heshaojun* @date 2020/12/29
 * @description TODO
 */
@Service
class ArticleDetailService implements IArticleDetailService {
    private final Log logger = LogFactory.getLog(ArticleDetailService.class)
    @Autowired
    private IArticleClassifyRepo articleClassifyRepo
    @Autowired
    private IArticleStatusRepo articleStatusRepo
    @Autowired
    private IArticleVisitsRepo articleVisitsRepo
    @Autowired
    private IArticleTitleRepo articleTitleRepo
    @Autowired
    private IArticleContentRepo articleContentRepo
    @Autowired
    private IArticleOriginalMarkRepo articleOriginalMarkRepo
    @Autowired
    private IArticleCommentRepo articleCommentRepo
    @Autowired
    private IArticleTypeRepo articleTypeRepo
    @Autowired
    private IArticleLabsRepo articleLabsRepo

    @Override
    RestRespVo getArticleDetail(String id) {
        RestRespVo respVo = new RestRespVo()
        respVo.code = 500
        respVo.result = "fail"
        respVo.msg = "获取数据失败"
        try {
            logger.info("获取文章id为${Long.valueOf(id)}的详情详情")
            ArticleDetailVo detailVo = new ArticleDetailVo()
            detailVo.title = articleTitleRepo.getById(id)
            detailVo.original = Boolean.valueOf(articleOriginalMarkRepo.getById(id))
            detailVo.type = articleTypeRepo.getById(id) == CommonConst.ARTICLE_TYPE_BLOG ? "博客" : "笔记"
            detailVo.comments = articleCommentRepo.getAllId(id).size().toString()
            int visits = articleVisitsRepo.getById(id)
            detailVo.visits = (int) (visits / 10000) > 0 ? "${(int) (visits / 10000)}万+" : "${visits}"
            detailVo.content = articleContentRepo.getById(id)
            String classifiesStr = articleClassifyRepo.getById(id)
            detailVo.classifyLabs = Arrays.asList(classifiesStr.split("\n"))
            String labsStr = articleLabsRepo.getById(id)
            detailVo.articleLabs = Arrays.asList(labsStr.split("\n"))
            detailVo.createTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Long.valueOf(id))
            respVo.data = detailVo
            respVo.code = 200
            respVo.result = "success"
            respVo.msg = "ok"
            logger.info("获取文章详情成功")
        } catch (Exception e) {
            e.printStackTrace()
        }
        respVo
    }
}
