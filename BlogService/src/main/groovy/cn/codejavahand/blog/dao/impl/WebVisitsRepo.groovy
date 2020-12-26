package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
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
    @CacheEvict(value = "webVisits", allEntries = true)
    void updateCreate(long visit) {
        TextFileOpUtils.write sysConfig.rootPath + "/${CommonConst.WEB_VISITS_FILE_NAME}", "$visit", false, true
    }

    @Override
    @Cacheable(value = "webVisits")
    long getWebVisits() {
        File file = new File(sysConfig.rootPath + "/${CommonConst.WEB_VISITS_FILE_NAME}")
        if (file.exists()) {
            if (file.isFile()) {
                return Long.valueOf(TextFileOpUtils.readAllString(sysConfig.rootPath + "/${CommonConst.WEB_VISITS_FILE_NAME}").replaceAll("\n", ""))
            }
        }
        return 0L
    }
}
