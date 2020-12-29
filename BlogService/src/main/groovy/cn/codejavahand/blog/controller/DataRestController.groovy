package cn.codejavahand.blog.controller

import cn.codejavahand.blog.service.IArticleDetailService
import cn.codejavahand.blog.service.IArticleListService
import cn.codejavahand.blog.service.ILabsService
import cn.codejavahand.blog.service.IWebStatisticService
import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 网站数据相关的rest接口，提供给网站数据接口
 */
@RestController()
@RequestMapping("/data")
class DataRestController {
    @Autowired
    private IWebStatisticService webStatisticService
    @Autowired
    private IArticleListService articleListService
    @Autowired
    private IArticleDetailService articleDetailService
    @Autowired
    private ILabsService labsService

    @PostMapping("/detail")
    RestRespVo getArticleDetailInfo(String articleId) {
        articleDetailService.getArticleDetail(articleId)
    }

    @PostMapping("/webStatistic")
    RestRespVo webStatistic() {
        webStatisticService.statistic()
    }

    @PostMapping("/articleList")
    RestRespVo articleList(int size, int page, String scope, String order, String mark) {
        articleListService.getArticleList(size, page, scope, order, mark)
    }

    @PostMapping("/classifyLabs")
    RestRespVo getClassifyLabs() {
        labsService.getAllClassifyLabs()
    }

    @PostMapping("/articleLabs")
    RestRespVo getArticleLabs() {
        labsService.getAllArticleLabs()
    }
}
