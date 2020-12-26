package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.service.ILoginService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service

import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest


@Service
class LoginService implements ILoginService {
    private final Log logger = LogFactory.getLog(LoginService.class)

    @Override
    void doLogin(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request
        httpServletRequest.getSession(true)
        chain.doFilter(request, response)
    }
}
