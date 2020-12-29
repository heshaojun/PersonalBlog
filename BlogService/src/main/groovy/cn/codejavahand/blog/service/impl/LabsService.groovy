package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.dao.IArticleClassifyRepo
import cn.codejavahand.blog.dao.IArticleCountRepo
import cn.codejavahand.blog.dao.IArticleLabsRepo
import cn.codejavahand.blog.service.ILabsService
import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author heshaojun* @date 2020/12/29
 * @description TODO
 */
@Service
class LabsService implements ILabsService {
    @Autowired
    private IArticleCountRepo articleCountRepo
    @Autowired
    private IArticleLabsRepo articleLabsRepo
    @Autowired
    private IArticleClassifyRepo articleClassifyRepo

    @Override
    RestRespVo getAllClassifyLabs() {
        RestRespVo respVo = new RestRespVo()
        respVo.code = 500
        respVo.result = "fail"
        try {
            Set<String> set = new HashSet<>()
            List<String> idList = articleCountRepo.countOnline()
            for (String id in idList) {
                String classifiesStr = articleClassifyRepo.getById(id)
                set.addAll(classifiesStr.split("\n"))
            }
            respVo.data = set.toList()
            respVo.code = 200
            respVo.result = "success"
        } catch (Exception e) {
            e.printStackTrace()
        }
        respVo
    }

    @Override
    RestRespVo getAllArticleLabs() {
        RestRespVo respVo = new RestRespVo()
        respVo.code = 500
        respVo.result = "fail"
        try {
            Set<String> set = new HashSet<>()
            List<String> idList = articleCountRepo.countOnline()
            for (String id in idList) {
                String labsStr = articleLabsRepo.getById(id)
                set.addAll(labsStr.split("\n"))
            }
            respVo.data = set.toList()
            respVo.code = 200
            respVo.result = "success"
        } catch (Exception e) {
            e.printStackTrace()
        }
        respVo
    }
}
