package cn.codejavahand.blog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication(scanBasePackages = "cn.codejavahand")
@ServletComponentScan
class AppStarter {

    static void main(String[] args) {
        SpringApplication.run(AppStarter, args)
    }

}
