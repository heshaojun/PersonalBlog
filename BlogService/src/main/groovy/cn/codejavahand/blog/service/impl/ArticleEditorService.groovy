package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleClassifyRepo
import cn.codejavahand.blog.dao.IArticleContentRepo
import cn.codejavahand.blog.dao.IArticleCountRepo
import cn.codejavahand.blog.dao.IArticleLabsRepo
import cn.codejavahand.blog.dao.IArticleOriginalMarkRepo
import cn.codejavahand.blog.dao.IArticleStatusRepo
import cn.codejavahand.blog.dao.IArticleSummaryRepo
import cn.codejavahand.blog.dao.IArticleTitleRepo
import cn.codejavahand.blog.dao.IArticleTypeRepo
import cn.codejavahand.blog.dao.IArticleVisitsRepo
import cn.codejavahand.blog.dao.IWebVisitsRepo
import cn.codejavahand.blog.service.IArticleEditorService
import cn.codejavahand.blog.service.vo.RestRespVo
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleEditorService implements IArticleEditorService {
    private final Log logger = LogFactory.getLog(ArticleEditorService.getClass())
    @Autowired
    private SysConfig sysConfig
    @Autowired
    private IArticleClassifyRepo articleClassifyRepo
    @Autowired
    private IArticleContentRepo articleContentRepo
    @Autowired
    private IArticleCountRepo articleCountRepo
    @Autowired
    private IArticleLabsRepo articleLabsRepo
    @Autowired
    private IArticleOriginalMarkRepo articleOriginalMarkRepo
    @Autowired
    private IArticleStatusRepo articleStatusRepo
    @Autowired
    private IArticleSummaryRepo articleSummaryRepo
    @Autowired
    private IArticleTitleRepo articleTitleRepo
    @Autowired
    private IArticleTypeRepo articleTypeRepo
    @Autowired
    private IArticleVisitsRepo articleVisitsRepo
    @Autowired
    private IWebVisitsRepo webVisitsRepo


    @Override
    RestRespVo updateCreate(String id, String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original) {
        RestRespVo respVo = new RestRespVo()
        respVo.setCode(500)
        respVo.setResult("fail")
        if (id == "" || id == null || id.replaceAll(" ", "") == "") {
            logger.info "开始添加文章"
            id = new Date().getTime().toString()
            File file = new File(sysConfig.rootPath + "/$id")
            while (true) {
                if (file.exists()) {
                    if (!file.isDirectory()) break
                } else break
                id = new Date().getTime().toString()
                file = new File(sysConfig.rootPath + "/$id")
            }
            file.mkdirs()
            try {
                respVo = addArticle id, title, summary, type, classifyLabs, articleLabs, content, original
            } catch (Exception e) {
                e.printStackTrace()
                try {
                    file.deleteDir()
                } catch (Exception e1) {
                }
                logger.error "添加文本失败", e
            }
            respVo
        } else {
            logger.info "开始更新文章"
            updateArticle id, title, summary, type, classifyLabs, articleLabs, content, original
        }

    }

    /**
     * 更新文章
     * @param id
     * @param title
     * @param summary
     * @param type
     * @param classifyLabs
     * @param articleLabs
     * @param content
     * @param original
     * @return
     */
    private RestRespVo updateArticle(String id, String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original) {
    }

    /**
     * 添加文章
     * @param id
     * @param title
     * @param summary
     * @param type
     * @param classifyLabs
     * @param articleLabs
     * @param content
     * @param original
     * @return
     */
    private RestRespVo addArticle(String id, String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original) {
        RestRespVo respVo = new RestRespVo()
        respVo.setCode(500)
        respVo.setResult("fail")
        if (title == null || title == "" || summary.replaceAll(" ", '') == "") {
            respVo.setMsg("文章标题为空")
            logger.info "文章标题为空"
        } else if (type != CommonConst.ARTICLE_TYPE_NOTE && type != CommonConst.ARTICLE_TYPE_BLOG) {
            respVo.setMsg('文章类型异常')
            logger.info "文章类型不存在"
        } else if (original != CommonConst.ORIGINAl_YES && original != CommonConst.ORIGINAl_NO) {
            respVo.setMsg('文章原创信息异常')
            logger.info "文章原创信息异常"
        } else if (summary == null || summary == "" || summary.replaceAll(" ", "") == "") {
            respVo.setMsg("文章摘要为空")
            logger.info "文章摘要为空"
        } else if (classifyLabs == null || classifyLabs == "" || classifyLabs.replaceAll(" ", "") == "") {
            respVo.setMsg("分类标签为空")
            logger.info "分类标签为空"
        } else if (articleLabs == null || articleLabs == "" || articleLabs.replaceAll(" ", "") == "") {
            respVo.setMsg("文章标签为空")
            logger.info "文章标签为空"
        } else if (original != 'yes' && original != 'no') {
            respVo.setMsg("原创标示异常")
            logger.info "原则标志为空"
        } else if (content == null || content == "" || content.replaceAll(" ", "") == "") {
            respVo.setMsg("文章内容为空")
            logger.info "文章内容为空"
        } else {
            logger.info "存储文章标题"
            articleTitleRepo.updateCreate id, title
            logger.info "存储文章摘要"
            articleSummaryRepo.updateCreate id, summary
            logger.info "存储分类标签"
            articleClassifyRepo.updateCreate id, classifyLabs
            logger.info "存储文章标签"
            articleLabsRepo.updateCreate id, articleLabs
            logger.info "存储文本内容"
            articleContentRepo.updateCreate id, content
            logger.info "存储文章类型信息"
            articleTypeRepo.updateCreate id, type
            logger.info "存储文章状态信息"
            articleStatusRepo.updateCreate id, CommonConst.ARTICLE_STATUS_OFFLINE
            logger.info "存储文章原创标识"
            articleOriginalMarkRepo.createUpdate id, original
            logger.info "创建文章访问量文件"
            articleVisitsRepo.updateCreate id, 0
            respVo.setCode(200)
            respVo.setResult('success')
            respVo.setMsg('ok')
            logger.info "添加文章成功"
        }
        respVo
    }

    @Override
    RestRespVo upLine(String id) {
        return null
    }

    @Override
    RestRespVo offLine(String id) {
        return null
    }
}
