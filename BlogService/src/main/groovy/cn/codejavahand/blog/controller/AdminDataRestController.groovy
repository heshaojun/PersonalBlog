package cn.codejavahand.blog.controller

import cn.codejavahand.blog.service.IArticleEditService
import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 管理员相关操作控制器，提供管理员相关操作的rest接口
 */
@RestController()
@RequestMapping("/admin")
class AdminDataRestController {
    @Autowired
    IArticleEditService articleEditorService

    @PostMapping("/updateCreate")
    RestRespVo addArticle(String id, String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original) {
        articleEditorService.updateCreate(id, title, summary, type, classifyLabs, articleLabs, content, original)
    }
}
