package cn.codejavahand.blog.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cache.annotation.EnableCaching

@SpringBootConfiguration
@EnableCaching
@ConfigurationProperties(prefix = "sys.conf")
class SysConfig {
    String rootPath
}
