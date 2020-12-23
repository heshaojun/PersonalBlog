package cn.codejavahand.blog.dao.repo.impl

import cn.codejavahand.blog.common.ComConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.entity.ArticleDetailDo
import cn.codejavahand.blog.dao.repo.IArticleDetailRepo
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleDetailRepo implements IArticleDetailRepo {
    private final Log logger = LogFactory.getLog(ArticleDetailRepo.class)
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
        String path = sysConfig.getRootPath() + "/" + id + "/"
        try {
            File contentFile = new File(path + ComConst.CONTENT_FILE_NAME)
            File classifyFile = new File(path + ComConst.CLASSIFY_FILE_NAME)
            File labsFile = new File(path + ComConst.ARTICLE_LAB_FILE_NAME)
            File titleFile = new File(path + ComConst.ARTICLE_TITLE_FILE_NAME)
            File summaryFile = new File(path + ComConst.ARTICLE_SUMMARY_FILE_NAME)
            File statusFIle = new File(path + ComConst.ARTICLE_STATUS_FILE_NAME)
            File typeFile = new File(path + ComConst.ARTICLE_TYPE_FILE_NAME)
            classifyFile.createNewFile()//创建存储文章分类标签的文件
            contentFile.createNewFile() //创建存储文章内容的文件
            labsFile.createNewFile() //创建存储文章标签的文件
            titleFile.createNewFile()//创建标题文件
            summaryFile.createNewFile()//创建存储文章摘要文件
            statusFIle.canExecute()//创建存储文章状态的文件
            typeFile.canExecute()//创建存储文章类型的文件

        } catch (Exception e) {
            try {
                new File(path).delete()
            } catch (Exception e1) {
            }
        }
    }
}
