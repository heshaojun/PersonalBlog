package cn.codejavahand.blog.controller

import cn.codejavahand.blog.dao.vo.RestRespVo
import cn.codejavahand.blog.service.IArticleEditorService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author heshaojun* @date 2020/12/18
 * @description TODO
 */
@RestController()
@RequestMapping("/admin")
class AdminDataRestController {
    @Autowired
    IArticleEditorService articleEditorService

    @PostMapping("/addArticle")
    RestRespVo addArticle(String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original) {
        articleEditorService.addArticle(title, summary, type, classifyLabs, articleLabs, content, original)
    }
}
