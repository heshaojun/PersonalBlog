package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleTitleRepo
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
class ArticleTitleRepo implements IArticleTitleRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = "articleTitle", key = "#id")
    void updateCreate(String id, String title) {
        TextFileOpUtils.write sysConfig.rootPath + "/$id/${ComConst.TITLE_FILE_NAME}", title, false, true
    }

    @Override
    @Cacheable(value = "articleTitle", key = "#id")
    String getById(String id) {
        TextFileOpUtils.readAllString sysConfig.rootPath + "/$id/${ComConst.TITLE_FILE_NAME}"
    }
}
