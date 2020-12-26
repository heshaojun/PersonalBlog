package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.dao.IWebVisitsRepo
import cn.codejavahand.blog.service.IWebVisitsCountService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WebVisitsCountService implements IWebVisitsCountService {
    private volatile int tempCount = 0
    private final Log logger = LogFactory.getLog(WebVisitsCountService.class)
    @Autowired
    private IWebVisitsRepo webVisitsRepo

    @Override
    long getWebVisits() {
        long count = 0
        synchronized (this) {
            try {
                count = webVisitsRepo.getWebVisits()
            } catch (Exception e) {
            }
            count += tempCount
            logger.info "当前网站访问量：$count"
        }
        return count
    }

    @Override
    void visit() {
        synchronized (this) {
            logger.info "有新的用户访问"
            tempCount += 1
        }
    }
    @Override
    void storage() {
        if (tempCount == 0) return
        synchronized (this) {
            long count = 0
            try {
                count = webVisitsRepo.getWebVisits()
            } catch (Exception e) {
            }
            count += tempCount
            tempCount = 0
            try {
                webVisitsRepo.updateCreate(count)
            } catch (Exception e) {
            }
            logger.info "存储网站反问统计数据成功"
        }
    }
}
