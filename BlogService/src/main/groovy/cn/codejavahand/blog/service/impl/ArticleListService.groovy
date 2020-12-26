package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.dao.IArticleClassifyRepo
import cn.codejavahand.blog.dao.IArticleCommentRepo
import cn.codejavahand.blog.dao.IArticleCountRepo
import cn.codejavahand.blog.dao.IArticleLabsRepo
import cn.codejavahand.blog.dao.IArticleSummaryRepo
import cn.codejavahand.blog.dao.IArticleTitleRepo
import cn.codejavahand.blog.dao.IArticleTypeRepo
import cn.codejavahand.blog.dao.IArticleVisitsRepo
import cn.codejavahand.blog.dao.IWebVisitsRepo
import cn.codejavahand.blog.service.IArticleListService
import cn.codejavahand.blog.service.vo.ArticleListVo
import cn.codejavahand.blog.service.vo.CommentVo
import cn.codejavahand.blog.service.vo.RestRespVo
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat
import java.util.logging.SimpleFormatter

@Service
class ArticleListService implements IArticleListService {
    private final Log logger = LogFactory.getLog(ArticleListService)
    @Autowired
    private IArticleCountRepo articleCountRepo
    @Autowired
    private IArticleVisitsRepo articleVisitsRepo
    @Autowired
    private IArticleCommentRepo articleCommentRepo
    @Autowired
    private IArticleTitleRepo articleTitleRepo
    @Autowired
    private IArticleSummaryRepo articleSummaryRepo
    @Autowired
    private IArticleTypeRepo articleTypeRepo
    @Autowired
    private IArticleClassifyRepo articleClassifyRepo
    @Autowired
    private IArticleLabsRepo articleLabsRepo

    @Override
    RestRespVo getArticleList(int size, int page, String scope, String order, String key) {
        RestRespVo respVo = new RestRespVo()
        respVo.setCode(200)
        respVo.setResult("success")
        if (page == 0) page = 1
        List<ArticleListVo.ArticleInfo> data = new ArrayList<>()
        if (key.startsWith("clab_")) {
            logger.info("按分类分页查询")
            String clab = key.replaceAll("clab_", "").replaceAll("\n", "")
            data = searchByClassifyLab(clab)
        } else if (key.startsWith("alab_")) {
            logger.info("按文章标签查询")
            String alab = key.replaceAll("alab_", "").replaceAll("\n", "")
            data = searchByArticleLab(alab)
        } else if (key.startsWith("kw_")) {
            logger.info("按关键字查询")
            String keyWord = key.replaceAll("kw_", "").replaceAll("\n", "")
            data = searchByKeyWord(keyWord)
        } else if (scope == "note") {
            logger.info "类型为笔记的"
            data = getAllNoteInfo()
        } else if (scope == "blog") {
            logger.info "类型为博客的"
            data = getAllBlogInfo()
        } else {
            logger.info "全部文章"
            data = getAllArticleInfo()
        }
        if (order == "visit") {
            data = data.sort { a, b -> a.visits - b.visits }
        }
        ArticleListVo articleListVo = new ArticleListVo()
        if ((page - 1) * size > data.size()) {
            logger.info "页码过大，返回尾页"
            data.size()/10
        }
        respVo
    }

    //获取所有上线等博客信息
    private List<ArticleListVo.ArticleInfo> getAllBlogInfo() {
        //获取所有上线的博客id
        List<String> blogIdList = articleCountRepo.countBlog(CommonConst.ARTICLE_STATUS_ONLINE)
        blogIdList = blogIdList.sort()//按照时间排序，id就是时间戳
        List<ArticleListVo.ArticleInfo> blogInfoList = new ArrayList<>()
        for (String id in blogIdList) {
            try {
                ArticleListVo.ArticleInfo articleInfo = new ArticleListVo.ArticleInfo()
                articleInfo.id = id
                articleInfo.comments = articleCommentRepo.getAllId(id).size()
                articleInfo.visits = articleVisitsRepo.getById(id)
                articleInfo.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(id))
                articleInfo.title = articleTitleRepo.getById(id)
                articleInfo.summary = articleSummaryRepo.getById(id)
                articleInfo.type = articleTypeRepo.getById(id)
                blogInfoList.add(articleInfo)
            } catch (Exception e) {
            }
        }
        blogInfoList
    }

    //获取所有笔记信息
    private List<ArticleListVo.ArticleInfo> getAllNoteInfo() {
        //获取所有上线的笔记id
        List<String> noteIdLis = articleCountRepo.countNote(CommonConst.ARTICLE_STATUS_ONLINE)
        noteIdLis = noteIdLis.sort()//按时间排序
        List<ArticleListVo.ArticleInfo> noteInfoList = new ArrayList<>()
        for (String id in noteIdLis) {
            try {
                ArticleListVo.ArticleInfo articleInfo = new ArticleListVo.ArticleInfo()
                articleInfo.id = id
                articleInfo.comments = articleCommentRepo.getAllId(id).size()
                articleInfo.visits = articleVisitsRepo.getById(id)
                articleInfo.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(id))
                articleInfo.title = articleTitleRepo.getById(id)
                articleInfo.summary = articleSummaryRepo.getById(id)
                articleInfo.type = articleTypeRepo.getById(id)
                noteInfoList.add(articleInfo)
            } catch (Exception e) {
            }
        }
        noteInfoList
    }
    //获取所有文章信息
    private List<ArticleListVo.ArticleInfo> getAllArticleInfo() {
        List<String> articleIdList = articleCountRepo.countOnline()
        articleIdList.sort()
        List<ArticleListVo.ArticleInfo> articleInfoList = new ArrayList<>()
        for (String id in articleIdList) {
            try {
                ArticleListVo.ArticleInfo articleInfo = new ArticleListVo.ArticleInfo()
                articleInfo.id = id
                articleInfo.comments = articleCommentRepo.getAllId(id).size()
                articleInfo.visits = articleVisitsRepo.getById(id)
                articleInfo.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(id))
                articleInfo.title = articleTitleRepo.getById(id)
                articleInfo.summary = articleSummaryRepo.getById(id)
                articleInfo.type = articleTypeRepo.getById(id)
                articleInfoList.add(articleInfo)
            } catch (Exception e) {
            }
        }
        articleInfoList
    }

    //实现简易文字搜索功能
    private List<ArticleListVo.ArticleInfo> searchByKeyWord(String keyWord) {
        List<ArticleListVo.ArticleInfo> allOnlineId = getAllArticleInfo()
        List<ArticleListVo.ArticleInfo> result = new ArrayList<>()
        for (ArticleListVo.ArticleInfo articleInfo in allOnlineId) {
            if (articleInfo.title.contains(keyWord) || articleInfo.summary.contains(keyWord)) {
                result.add(articleInfo)
            }
        }
        result
    }
    //根据分类标签查询
    private List<ArticleListVo.ArticleInfo> searchByClassifyLab(String clab) {
        List<ArticleListVo.ArticleInfo> allOnlineId = getAllArticleInfo()
        List<ArticleListVo.ArticleInfo> result = new ArrayList<>()
        for (ArticleListVo.ArticleInfo articleInfo in allOnlineId) {
            String clabsStr = articleClassifyRepo.getById(articleInfo.id)
            String[] clabs = clabsStr.split("\n")
            for (String l in clabs) {
                if (clab == l.replaceAll("\n", "")) {
                    result.add(articleInfo)
                    break
                }
            }
        }
        result
    }
    //根据文章标签查询
    private List<ArticleListVo.ArticleInfo> searchByArticleLab(String alab) {
        List<ArticleListVo.ArticleInfo> allOnlineId = getAllArticleInfo()
        List<ArticleListVo.ArticleInfo> result = new ArrayList<>()
        for (ArticleListVo.ArticleInfo articleInfo in allOnlineId) {
            String alabsStr = articleLabsRepo.getById(articleInfo.id)
            String[] alabs = clabsStr.split("\n")
            for (String l in alabs) {
                if (alab == l.replaceAll("\n", "")) {
                    result.add(articleInfo)
                    break
                }
            }
        }
        result
    }
}
