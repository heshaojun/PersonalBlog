package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleVisitsRepo
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
class ArticleVisitsRepo implements IArticleVisitsRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = "articleVisits", key = "#id")
    void updateCreate(String id, int visits) {
        TextFileOpUtils.write sysConfig.rootPath + "/$id/${ComConst.VISITS_FILE_NAME}", false, true
    }

    @Override
    @Cacheable(value = "articleVisits", key = "#id")
    int getById(String id) {
        Integer.valueOf(TextFileOpUtils.readAllString(sysConfig.rootPath + "/$id/${ComConst.VISITS_FILE_NAME}"))
    }
}
