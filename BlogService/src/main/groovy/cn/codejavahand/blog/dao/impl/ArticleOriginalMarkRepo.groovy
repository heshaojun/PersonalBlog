package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleOriginalMarkRepo
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
class ArticleOriginalMarkRepo implements IArticleOriginalMarkRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = "originalMark", key = "#id")
    void createUpdate(String id, String mark) {
        TextFileOpUtils.write sysConfig.rootPath + "/$id/${CommonConst.ORIGINAl_FILE_NAME}", mark, false, true
    }

    @Override
    @Cacheable(value = "originalMark", key = "#id")
    String getById(String id) {
        TextFileOpUtils.readAllString sysConfig.rootPath + "/$id/${CommonConst.ORIGINAl_FILE_NAME}"
    }
}
