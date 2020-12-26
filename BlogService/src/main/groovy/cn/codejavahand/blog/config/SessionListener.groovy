package cn.codejavahand.blog.config

import cn.codejavahand.blog.service.IWebVisitsCountService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

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
