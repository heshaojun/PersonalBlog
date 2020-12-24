package cn.codejavahand.blog.dao.repo.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.entity.ArticleDetailDo
import cn.codejavahand.blog.dao.repo.IArticleDetailRepo
import cn.codejavahand.blog.utils.TextFileOpUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ArticleDetailRepo implements IArticleDetailRepo {
    private final Log logger = LogFactory.getLog(ArticleDetailRepo.class)
    @Autowired
    private SysConfig sysConfig

    @Override
    @Cacheable(value = "articleDetail", key = "#id")
    ArticleDetailDo getById(String id) {
        logger.info "通过id查找文章"
        String path = sysConfig.getRootPath() + "/" + id + "/"
        ArticleDetailDo articleDetailDo = new ArticleDetailDo()
        try {
            //存储文章内容
            articleDetailDo.content = TextFileOpUtils.readAllString path + ComConst.CONTENT_FILE_NAME
            //存储分类标签
            articleDetailDo.classify = TextFileOpUtils.readAllString path + ComConst.CLASSIFY_FILE_NAME
            //存储文章标签
            articleDetailDo.labs = TextFileOpUtils.readAllString path + ComConst.ARTICLE_LAB_FILE_NAME
            //存储文章标题
            articleDetailDo.title = TextFileOpUtils.readAllString path + ComConst.ARTICLE_TITLE_FILE_NAME
            //存储文章摘要
            articleDetailDo.summary = TextFileOpUtils.readAllString path + ComConst.ARTICLE_SUMMARY_FILE_NAME
            //存储文章类型
            articleDetailDo.type = TextFileOpUtils.readAllString path + ComConst.ARTICLE_TYPE_FILE_NAME
            //存储文章原创信息
            articleDetailDo.original = TextFileOpUtils.readAllString path + ComConst.ARTICLE_ORIGIN_FILE_NAME
            //存储文章状态
            articleDetailDo.status = TextFileOpUtils.readAllString path + ComConst.ARTICLE_STATUS_FILE_NAME
        } catch (Exception e) {
            e.printStackTrace()
            logger.error "查询文章失败"
            throw new Exception("查询文章失败", e)
        }
        return articleDetailDo
    }

    @Override
    @CacheEvict(value = ["articleInfoList", "articleInfoListByTime", "articleInfoListByVisit", "articleInfoListByTimeAnScop", "articleInfoListByVisitAnScop", "articleDetail"], key = "#id", allEntries = true)
    void update(String id, ArticleDetailDo articleDetailDo) {
        logger.info "开始更新文章"
        String path = sysConfig.getRootPath() + "/" + id + "/"
        try {
            if (articleDetailDo.content != null && !"" != articleDetailDo.content && articleDetailDo.content.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.CONTENT_FILE_NAME, articleDetailDo.content, false, true
            //存储分类标签
            if (articleDetailDo.classify != null && !"" != articleDetailDo.classify && articleDetailDo.classify.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.CLASSIFY_FILE_NAME, articleDetailDo.classify, false, true
            //存储文章标签
            if (articleDetailDo.labs != null && !"" != articleDetailDo.labs && articleDetailDo.labs.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.ARTICLE_LAB_FILE_NAME, articleDetailDo.labs, false, true
            //存储文章标题
            if (articleDetailDo.title != null && !"" != articleDetailDo.title && articleDetailDo.title.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.ARTICLE_TITLE_FILE_NAME, articleDetailDo.title, false, true
            //存储文章摘要
            if (articleDetailDo.summary != null && !"" != articleDetailDo.summary && articleDetailDo.summary.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.ARTICLE_SUMMARY_FILE_NAME, articleDetailDo.summary, false, true
            //存储文章类型
            if (articleDetailDo.type != null && !"" != articleDetailDo.type && articleDetailDo.type.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.ARTICLE_TYPE_FILE_NAME, articleDetailDo.type, false, true
            //存储文章原创信息
            if (articleDetailDo.original != null && !"" != articleDetailDo.original && articleDetailDo.original.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.ARTICLE_ORIGIN_FILE_NAME, articleDetailDo.original, false, true
            //存储文章状态
            if (articleDetailDo.status != null && !"" != articleDetailDo.status && articleDetailDo.status.replaceAll(" ", "") != "")
                TextFileOpUtils.write path + ComConst.ARTICLE_STATUS_FILE_NAME, articleDetailDo.status, false, true
            TextFileOpUtils.write path + ComConst.VISITS_COUNT_FILE_NAME, "o", false, true
        } catch (Exception e) {
            e.printStackTrace()
            logger.error "更新文章错误"
            throw new Exception("更新文章错误", e)
        }
    }

    @Override
    @CacheEvict(value = ["articleInfoList", "articleIdList", "articleInfoListByTime", "articleInfoListByVisit", "articleInfoListByTimeAnScop", "articleInfoListByVisitAnScop"])
    void addArticle(String id, ArticleDetailDo articleDetailDo) {
        logger.info "开始存储文章"
        String path = sysConfig.getRootPath() + "/" + id + "/"
        try {
            //存储文章内容
            TextFileOpUtils.write path + ComConst.CONTENT_FILE_NAME, articleDetailDo.content, false, true
            //存储分类标签
            TextFileOpUtils.write path + ComConst.CLASSIFY_FILE_NAME, articleDetailDo.classify, false, true
            //存储文章标签
            TextFileOpUtils.write path + ComConst.ARTICLE_LAB_FILE_NAME, articleDetailDo.labs, false, true
            //存储文章标题
            TextFileOpUtils.write path + ComConst.ARTICLE_TITLE_FILE_NAME, articleDetailDo.title, false, true
            //存储文章摘要
            TextFileOpUtils.write path + ComConst.ARTICLE_SUMMARY_FILE_NAME, articleDetailDo.summary, false, true
            //存储文章类型
            TextFileOpUtils.write path + ComConst.ARTICLE_TYPE_FILE_NAME, articleDetailDo.type, false, true
            //存储文章原创信息
            TextFileOpUtils.write path + ComConst.ARTICLE_ORIGIN_FILE_NAME, articleDetailDo.original, false, true
            //存储文章状态
            TextFileOpUtils.write path + ComConst.ARTICLE_STATUS_FILE_NAME, articleDetailDo.status, false, true
        } catch (Exception e) {
            try {
                new File(path).delete()
            } catch (Exception e1) {
            }
            throw new Exception("存储文章失败", e)
            logger.error "文章存储失败", e
        }
        logger.info "文章存储成功"
    }
}
