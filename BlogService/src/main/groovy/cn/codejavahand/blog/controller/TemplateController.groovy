package cn.codejavahand.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 模版控制器，对于部分需要视图解析的页面进行模型视图解析
 */
@Controller
class TemplateController {

    @RequestMapping("/")
    String rootHome() {
        'redirect:home.html'
    }

    @RequestMapping('/home')
    String home() {
        'redirect:home.html'
    }

    @RequestMapping("/admin/create")
    String toEditor(Model model) {
        model.addAttribute("actionUrl", "/admin/addArticle")
        'editor'
    }

    @RequestMapping("/admin/alter/{id}")
    String alter(@PathVariable("id") String id) {
        'editor'
    }
}
