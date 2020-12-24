package cn.codejavahand.blog.controller

import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * @author heshaojun* @date 2020/12/18
 * @description TODO
 */
@RestController("/data")
class DataRestController {
    @GetMapping("/detail/{id}")
    RestRespVo getArticleDetailInfo(@PathVariable("id") String id) {

    }
}
