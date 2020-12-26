package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleStatusRepo
import cn.codejavahand.blog.utils.TextFileOpUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO
 */
@Service
class ArticleStatusRepo implements IArticleStatusRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = ["articleStatus", "allArticleIdList", "allOfflineArticleIdList", "allOnlineArticleIdList"], key = "#id", allEntries = true)
    void updateCreate(String id, String status) {
        TextFileOpUtils.write sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.STATUS_FILE_NAME}", status, false, true
    }

    @Override
    @Cacheable(value = "articleStatus", key = "#id")
    String getById(String id) {
        TextFileOpUtils.readAllString sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.STATUS_FILE_NAME}"
    }
}
