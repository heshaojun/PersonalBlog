package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleLabsRepo
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
class ArticleLabsRepo implements IArticleLabsRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = "articleLabs", key = "#id")
    void updateCreate(String id, String labs) {
        TextFileOpUtils.write sysConfig.rootPath + "/$id/${CommonConst.LABS_FILE_NAME}", labs, false, true
    }

    @Override
    @Cacheable(value = "articleLabs", key = "#id")
    List<String> getById(String id) {
        TextFileOpUtils.readAllLine sysConfig.rootPath + "/$id/${CommonConst.LABS_FILE_NAME}"
    }
}
