package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
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
        TextFileOpUtils.write sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.VISITS_FILE_NAME}", "$visits", false, true
    }

    @Override
    @Cacheable(value = "articleVisits", key = "#id")
    int getById(String id) {
        Integer.valueOf(TextFileOpUtils.readAllString(sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.VISITS_FILE_NAME}").replaceAll("\n", ""))
    }
}
