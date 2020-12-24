package cn.codejavahand.blog.dao.repo.impl

import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.entity.ArticleInfoDo
import cn.codejavahand.blog.dao.repo.IArticleInfoRepo
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ArticleInfoRepo implements IArticleInfoRepo {
    private final Log logger = LogFactory.getLog(ArticleInfoRepo.class)
    @Autowired
    private SysConfig sysConfig

    @Override
    @Cacheable(value = "articleInfoList")
    List<ArticleInfoDo> getAllArticleInfo() {
        logger.info "获取全部文章信息"
        return null
    }

    @Override
    @Cacheable(value = "articleIdList")
    List<String> getAllId() {
        logger.info "获取所有文章id"
        File rootFile = new File(sysConfig.getRootPath())
        File[] files = rootFile.listFiles({ File file ->
            file.isDirectory() && file.listFiles().size() > 0
        } as FileFilter)
        List<String> idList = new ArrayList<>(files.size())
        idList.with {
            for (File file in files) {
                add file.name
            }
        }
        idList
    }
}
