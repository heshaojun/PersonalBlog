package cn.codejavahand.blog.dao.repo.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.entity.ArticleCommentDo
import cn.codejavahand.blog.dao.repo.IArticleCommentRepo
import cn.codejavahand.blog.utils.TextFileOpUtils
import org.apache.commons.codec.binary.Base64
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
    private SysConfig sysConfig

    @Override
    List<ArticleCommentDo> getCommentsById(String id) {
        logger.info("开始获取文章所有评论")
        List<String> list = countId(id)
        list.sort()
        String path = sysConfig.getRootPath() + "/" + id + "/" + ComConst.ARTICLE_COMMENTS_PATH
        List<ArticleCommentDo> commentDoList = new ArrayList<>()
        if (list != null && list.size() > 0) {
            commentDoList.with {
                list.forEach({
                    try {
                        List<String> msg = TextFileOpUtils.readAllLine(path + "/${it}.txt")
                        add new ArticleCommentDo(id, it, new String(Base64.decodeBase64(msg.get(0))), new String(Base64.decodeBase64(msg.get(1))))
                    } catch (Exception e) {
                        e.printStackTrace()
                    }
                })
            }
        }
        commentDoList
    }

    @Override
    void addOneComment(String id, ArticleCommentDo articleCommentDo) {
        logger.info "删除评论"
        String path = sysConfig.getRootPath() + "/" + id + "/" + ComConst.ARTICLE_COMMENTS_PATH
        new File(path).mkdirs()
        long commentId = new Date().getTime()
        File file = new File(path + "/" + commentId + ".txt")
        while (true) {
            if (file.exists()) break
            commentId = new Date().getTime().toString()
            file = new File(path + "/" + commentId + ".txt")
        }
        TextFileOpUtils.write(path + "/" + commentId + ".txt", Base64.encodeBase64String(articleCommentDo.userName.getBytes()) + "\n", false, true)
        TextFileOpUtils.write(path + "/" + commentId + ".txt", Base64.encodeBase64String(articleCommentDo.commentMsg.getBytes()) + "\n", true, false)
    }

    @Override
    @CacheEvict(value = "commentIds", key = "#id")
    void removeOneComment(String id, String commentId) {
        logger.info "删除评论"
        File file = new File(sysConfig.getRootPath() + "/" + id + "/" + ComConst.ARTICLE_COMMENTS_PATH + "/" + commentId + ".txt")
        file.deleteOnExit()
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
