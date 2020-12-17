package cn.codejavahand.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver

/**
 * @author heshaojun* @date 2020/12/17
 * @description TODO
 */
@Controller
@RequestMapping("/")
class CustomController {
    @GetMapping
    ModelAndView home() {
        new ModelAndView("main_template")
    }

}
