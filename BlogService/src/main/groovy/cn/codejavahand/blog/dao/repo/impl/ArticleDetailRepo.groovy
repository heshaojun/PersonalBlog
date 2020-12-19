package cn.codejavahand.blog.dao.repo.impl

import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.entity.ArticleDetailDo
import cn.codejavahand.blog.dao.repo.IArticleDetailRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleDetailRepo implements IArticleDetailRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    ArticleDetailDo getById(String id) {
        return null
    }

    @Override
    void update(String id, ArticleDetailDo articleDetailDo) {

    }

    @Override
    void addArticle(String id, ArticleDetailDo articleDetailDo) {
        String path = sysConfig.getRootPath() + "/" + id
        try {

        } catch (Exception e) {
            try {
                new File(path).delete()
            } catch (Exception e1) {
            }
        }
    }
}
