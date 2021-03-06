package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleContentRepo
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
class ArticleContentRepo implements IArticleContentRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = ["articleContent", "allArticleIdList"], key = "#id", allEntries = true)
    void updateCreate(String id, String content) {
        TextFileOpUtils.write sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.CONTENT_FILE_NAME}", content, false, true
    }

    @Override
    @Cacheable(value = "articleContent", key = "#id")
    String getById(String id) {
        TextFileOpUtils.readAllString sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.CONTENT_FILE_NAME}"
    }

}
