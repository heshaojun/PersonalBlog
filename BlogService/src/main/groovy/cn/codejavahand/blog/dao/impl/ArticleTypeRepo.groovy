package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleTypeRepo
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
class ArticleTypeRepo implements IArticleTypeRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = ["articleType", "countNote", "countBlog"], key = "#id", allEntries = true)
    void updateCreate(String id, String type) {
        TextFileOpUtils.write sysConfig.rootPath + "/$id/${CommonConst.TYPE_FILE_NAME}", type, false, true
    }

    @Override
    @Cacheable(value = "articleType", key = "#id")
    String getById(String id) {
        TextFileOpUtils.readAllString sysConfig.rootPath + "/$id/${CommonConst.TYPE_FILE_NAME}"
    }
}
