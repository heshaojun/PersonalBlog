package cn.codejavahand.blog.config

import cn.codejavahand.blog.service.IWebVisitsCountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 定时任务注册，注册相关定时任务，定时执行，缓解频繁执行任务带来的短时间内负载过大
 */
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
