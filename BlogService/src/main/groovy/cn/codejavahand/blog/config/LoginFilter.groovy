package cn.codejavahand.blog.config

import cn.codejavahand.blog.service.ILoginService
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

/**
 * @author heshaojun* @date 2020/12/17
 * @description 登陆过滤器，实现管理员登陆
 */
@WebFilter(urlPatterns = "/*")
class LoginFilter implements Filter {
    @Autowired
    ILoginService loginService;

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        loginService.doLogin request, response, chain
    }

    @Override
    void destroy() {
    }
}
