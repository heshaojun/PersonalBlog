package cn.codejavahand.blog.config

import cn.codejavahand.blog.service.IWebVisitsCountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class SysAutoScheduled {
    @Autowired
    private IWebVisitsCountService webVisitsCountService

    @Scheduled(fixedRate = 60000L)
    void storeWebVisitsData() {
        webVisitsCountService.storage()
    }
}
