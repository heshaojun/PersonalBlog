package cn.codejavahand.blog.config

import cn.codejavahand.blog.service.IWebVisitsCountService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 注册seesion创建监听器，seesion创建时为新用户访问，网站访问记录添加1
 *
 */
@WebListener
@Component
class SessionListener implements HttpSessionListener {
    private final Log logger = LogFactory.getLog(SessionListener.class)
    @Autowired
    private IWebVisitsCountService webVisitsCountService

    @Override
    void sessionCreated(HttpSessionEvent se) {
        try {
            logger.info "生成新的session"
            webVisitsCountService.visit()
        } catch (Exception e) {
        }
    }
}
