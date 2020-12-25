package cn.codejavahand.blog.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @author heshaojun* @date 2020/12/18
 * @description TODO
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
