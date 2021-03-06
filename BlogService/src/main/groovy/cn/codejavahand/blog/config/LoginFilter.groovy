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
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO web过滤器实现 session 创建和管理员登录功能
 * */
@WebFilter(urlPatterns = "/*")
class LoginFilter implements Filter {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private SysConfig sysConfig

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request
        //只运行带域名的连接访问
        String host = servletRequest.getHeader("host")
        if (!sysConfig.domainNames.contains(host)) {
            return
        }
        loginService.doLogin request, response, chain
    }

    @Override
    void destroy() {
    }
}
