package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleCountRepo
import cn.codejavahand.blog.dao.IArticleStatusRepo
import cn.codejavahand.blog.dao.IArticleTypeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO
 */
@Service
class ArticleCountRepo implements IArticleCountRepo {

    @Autowired
    private SysConfig sysConfig
    @Autowired
    private IArticleTypeRepo articleTypeRepo
    @Autowired
    private IArticleStatusRepo articleStatusRepo

    @Override
    List<String> countNote(String status) {
        List<String> idList = countAll()
        List<String> filteredIds = new ArrayList<>()
        for (String id in idList) {
            try {
                if (status == articleStatusRepo.getById(id).replaceAll("\n", "") && CommonConst.ARTICLE_TYPE_NOTE == articleTypeRepo.getById(id).replaceAll("\n", "")) {
                    filteredIds.add(id)
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        filteredIds
    }

    @Override
    List<String> countBlog(String status) {
        List<String> idList = countAll()
        List<String> filteredIds = new ArrayList<>()
        for (String id in idList) {
            try {
                if (status == articleStatusRepo.getById(id).replaceAll("\n", "") && CommonConst.ARTICLE_TYPE_BLOG == articleTypeRepo.getById(id).replaceAll("\n", "")) {
                    filteredIds.add(id)
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        filteredIds
    }

    @Override
    @Cacheable(value = "allArticleIdList")
    List<String> countAll() {
        File[] files = new File(sysConfig.rootPath + "/${CommonConst.ARTICLE_PATH}").listFiles({ File file -> file.isDirectory() } as FileFilter)
        List<String> idList = new ArrayList<>()
        for (File file in files) {
            idList.add file.name
        }
        idList
    }

    @Override
    @Cacheable(value = "allOnlineArticleIdList")
    List<String> countOnline() {
        List<String> allId = countAll()
        List<String> online = new ArrayList<>()
        for (String id in allId) {
            if (CommonConst.ARTICLE_STATUS_ONLINE == articleStatusRepo.getById(id).replaceAll("\n", "")) {
                online.add(id)
            }
        }
        online
    }

    @Override
    @Cacheable(value = "allOfflineArticleIdList")
    List<String> countOffLine() {
        List<String> allId = countAll()
        List<String> offline = new ArrayList<>()
        for (String id in allId) {
            if (CommonConst.ARTICLE_STATUS_ONLINE == articleStatusRepo.getById(id).replaceAll("\n", "")) {
                offline.add(id)
            }
        }
        offline
    }

}
