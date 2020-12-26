package cn.codejavahand.blog.controller

import cn.codejavahand.blog.service.IArticleListService
import cn.codejavahand.blog.service.IWebStatisticService
import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author heshaojun* @date 2020/12/18
 * @description TODO
 */
@RestController()
@RequestMapping("/data")
class DataRestController {
    @Autowired
    private IWebStatisticService webStatisticService
    @Autowired
    private IArticleListService articleListService

    @GetMapping("/detail/{id}")
    RestRespVo getArticleDetailInfo(@PathVariable("id") String id) {

    }

    @PostMapping("/webStatistic")
    RestRespVo webStatistic() {
        webStatisticService.statistic()
    }

    @PostMapping("/articleList")
    RestRespVo articleList(int size, int page, String scope, String order, String mark) {
        articleListService.getArticleList(size, page, scope, order, mark)
    }
}
