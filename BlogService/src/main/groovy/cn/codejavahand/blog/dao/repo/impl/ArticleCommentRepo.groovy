package cn.codejavahand.blog.dao.repo.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.entity.ArticleCommentDo
import cn.codejavahand.blog.dao.repo.IArticleCommentRepo
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * @author heshaojun* @date 2020/12/24
 * @description TODO
 */
@Service
class ArticleCommentRepo implements IArticleCommentRepo {
    private final Log logger = LogFactory.getLog(ArticleCommentRepo.class)
    @Autowired
    private SysConfig sysConfig = new SysConfig()

    @Override
    ArticleCommentDo getById(String id, String commentId) {
        return null
    }

    @Override
    void addOneComment(String id, String commentId, ArticleCommentDo articleCommentDo) {

    }

    @Override
    @CacheEvict(value = "commentIds", key = "#id")
    void removeOneComment(String id, String commentId) {

    }

    @Override
    @Cacheable(value = "commentIds", key = "#id")
    List<String> countId(String id) {
        logger.info "获取文章所有评论id"
        String path = sysConfig.getRootPath() + "/" + id + "/" + ComConst.ARTICLE_COMMENTS_PATH
        List<String> list = new ArrayList<>()
        try {
            File pathFile = new File(path)
            if (pathFile.isDirectory()) {
                File[] files = pathFile.listFiles({ File file -> file.name.endsWith(".txt") } as FileFilter)
                list.with {
                    for (File file in files) {
                        add file.name.replaceAll(".txt", "")
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace()
            logger.error "获取文章评论id失败"
            throw new Exception("获取文章评论id失败", e)
        }
        return list
    }
}
