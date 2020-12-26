package cn.codejavahand.blog.service

import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 登陆服务接口，提供登陆拦截器登陆接口
 */
interface ILoginService {
    void doLogin(ServletRequest request, ServletResponse response, FilterChain chain)
}