package cn.codejavahand.blog.dao.repo.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.repo.IArticleVisitRepo
import cn.codejavahand.blog.utils.TextFileOpUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * @author heshaojun* @date 2020/12/24
 * @description TODO
 */
@Service
class ArticleVisitRepo implements IArticleVisitRepo {
    private Log logger = LogFactory.getLog(ArticleVisitRepo.class)
    @Autowired
    private SysConfig sysConfig

    @Override
    @Cacheable(value = "articleVisits", key = "#id")
    int getById(String id) {
        logger.info "获取文章的阅读量"
        String path = sysConfig.getRootPath() + "/" + id + "/"
        Integer.valueOf(TextFileOpUtils.readAllString(path + ComConst.ARTICLE_STATUS_FILE_NAME))
    }

    @Override
    @CachePut(value = "articleVisits", key = "#id")
    int addOne(String id) {
        logger.info("增加一次文章的阅读量")
        String path = sysConfig.getRootPath() + "/" + id + "/"
        int count = getById(id) + 1
        TextFileOpUtils.write path + ComConst.ARTICLE_STATUS_FILE_NAME, "$count", false, false
        return count
    }
}
