package cn.codejavahand.blog.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cache.annotation.EnableCaching

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 系统配置类主要配置一些全局属性
 */
@SpringBootConfiguration
@EnableCaching
@ConfigurationProperties(prefix = "sys.conf")
class SysConfig {
    String rootPath
}
