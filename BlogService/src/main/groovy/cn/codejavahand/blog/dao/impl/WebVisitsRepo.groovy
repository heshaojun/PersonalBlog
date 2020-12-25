package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IWebVisitsRepo
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
class WebVisitsRepo implements IWebVisitsRepo {

    @Autowired
    private SysConfig sysConfig

    @Override
    @CacheEvict(value = "webVisits")
    void updateCreate(long visit) {
        TextFileOpUtils.write sysConfig.rootPath + "${ComConst.WEB_VISITS_FILE_NAME}", "$visit", false, true
    }

    @Override
    @Cacheable(value = "webVisits")
    long getWebVisits() {
        Long.valueOf(TextFileOpUtils.readAllString(sysConfig.rootPath + "${ComConst.WEB_VISITS_FILE_NAME}"))
    }
}
