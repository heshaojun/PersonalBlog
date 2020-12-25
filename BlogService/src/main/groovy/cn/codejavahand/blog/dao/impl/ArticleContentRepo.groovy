package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.ComConst
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
    @CacheEvict(value = "articleContent", key = "#id")
    void updateCreate(String id, String content) {
        TextFileOpUtils.write sysConfig.rootPath + "/$id/${ComConst.CONTENT_FILE_NAME}", content, false, true
    }

    @Override
    @Cacheable(value = "articleContent", key = "#id")
    String getById(String id) {
        TextFileOpUtils.readAllString sysConfig.rootPath + "/$id/${ComConst.CONTENT_FILE_NAME}"
    }
}
