package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleClassifyRepo
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
class ArticleClassifyRepo implements IArticleClassifyRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = "articleClassifyLabs", key = "#id")
    void updateCreate(String id, String classifyLabs) {
        TextFileOpUtils.write sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.CLASSIFY_FILE_NAME}", classifyLabs, false, true
    }

    @Override
    @Cacheable(value = "articleClassifyLabs", key = "#id")
    String getById(String id) {
        TextFileOpUtils.readAllString sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.CLASSIFY_FILE_NAME}"

    }
}
