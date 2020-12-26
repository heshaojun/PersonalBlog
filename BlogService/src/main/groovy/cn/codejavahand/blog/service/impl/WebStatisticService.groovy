package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.dao.IArticleCountRepo
import cn.codejavahand.blog.dao.IMsgBoardRepo
import cn.codejavahand.blog.dao.IWebVisitsRepo
import cn.codejavahand.blog.service.IWebStatisticService
import cn.codejavahand.blog.service.IWebVisitsCountService
import cn.codejavahand.blog.service.vo.RestRespVo
import cn.codejavahand.blog.service.vo.WebStatisticVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WebStatisticService implements IWebStatisticService {

    @Autowired
    private IWebVisitsCountService webVisitsCountService
    @Autowired
    private IArticleCountRepo articleCountRepo
    @Autowired
    private IMsgBoardRepo msgBoardRepo

    @Override
    RestRespVo statistic() {
        RestRespVo respVo = new RestRespVo()
        respVo.with {
            WebStatisticVo statisticVo = new WebStatisticVo()
            code = 200
            result = "success"
            statisticVo.with {
                try {
                    visits = webVisitsCountService.getWebVisits()
                } catch (Exception e) {
                    e.printStackTrace()
                }
                try {
                    blogs = Long.valueOf(articleCountRepo.countBlog(CommonConst.ARTICLE_STATUS_ONLINE).size())
                } catch (Exception e) {
                    e.printStackTrace()
                }
                try {
                    notes = Long.valueOf(articleCountRepo.countNote(CommonConst.ARTICLE_STATUS_ONLINE).size())
                } catch (Exception e) {
                    e.printStackTrace()
                }
                articles = notes + blogs
                try {
                    comments = Long.valueOf(msgBoardRepo.getAllMsgId().size())
                } catch (Exception e) {
                    e.printStackTrace()
                }

            }
            data = statisticVo
        }
        respVo
    }
}
