package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.dao.*
import cn.codejavahand.blog.service.IArticleListService
import cn.codejavahand.blog.service.vo.ArticleListVo
import cn.codejavahand.blog.service.vo.RestRespVo
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.text.SimpleDateFormat

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
        ArticleListVo articleListVo = new ArticleListVo()
        if (key.startsWith("clab_")) {
            logger.info("按分类分页查询")
            String clab = key.replaceAll("clab_", "").replaceAll("\n", "")
            data = searchByClassifyLab(clab)
            articleListVo.articleCount = data.size()
        } else if (key.startsWith("alab_")) {
            logger.info("按文章标签查询")
            String alab = key.replaceAll("alab_", "").replaceAll("\n", "")
            data = searchByArticleLab(alab)
            articleListVo.articleCount = data.size()
        } else if (key.startsWith("kw_")) {
            logger.info("按关键字查询")
            String keyWord = key.replaceAll("kw_", "").replaceAll("\n", "")
            data = searchByKeyWord(keyWord)
            articleListVo.articleCount = data.size()
        } else {
            def blogList = getAllBlogInfo()
            def noteList = getAllNoteInfo()
            def articleList = getAllArticleInfo()
            articleListVo.articleCount = articleList.size()
            articleListVo.noteCount = noteList.size()
            articleListVo.blogCount = blogList.size()
            if (scope == "note") {
                logger.info "获取类型为笔记的"
                data = noteList
            } else if (scope == "blog") {
                logger.info "获取类型为博客的"
                data = blogList
            } else {
                logger.info "获取全部文章数据"
                data = articleList
            }
        }
        //按访问数量排序
        if (order == "visit") {
            data = data.sort { a, b -> a.visits - b.visits }
        }
        if (data.size() % size > 0) {
            articleListVo.totalPage = (int) (data.size() / size) + 1
        } else {
            articleListVo.totalPage = (int) (data.size() / size)
        }
        articleListVo.pageSize = size
        articleListVo.orderBy = order
        articleListVo.listCount = size

        if (data.size() != 0) {
            //进行分页处理
            int fromIndex = 0
            int toIndex = 0
            if (page * size > data.size()) {
                logger.info "页码过大，返回尾页"
                page = (int) (data.size() / size) + 1
                fromIndex = ((int) (data.size() / size)) * size
                if (fromIndex != 0) fromIndex -= 1
                toIndex = data.size()
                articleListVo.listCount = data.size() % size
            } else {
                if (page - 1 > 0) fromIndex = (page - 1) * size - 1
                toIndex = page * size
            }
            articleListVo.list = data.subList(fromIndex, toIndex)
        } else {
            articleListVo.list = []
        }
        articleListVo.currentPage = page
        respVo.data = articleListVo
        respVo
    }

    //获取所有上线等博客信息
    private List<ArticleListVo.ArticleInfo> getAllBlogInfo() {
        //获取所有上线的博客id
        List<String> blogIdList = articleCountRepo.countBlog(CommonConst.ARTICLE_STATUS_ONLINE)
        blogIdList = blogIdList.sort().reverse()//按照时间排序，id就是时间戳
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
                String type = articleTypeRepo.getById(id).replaceAll("\n", "")
                if (type == CommonConst.ARTICLE_TYPE_BLOG) {
                    articleInfo.type = "博客"
                } else if (type == CommonConst.ARTICLE_TYPE_NOTE){
                    articleInfo.type = "笔记"
                }
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
        noteIdLis = noteIdLis.sort().reverse()//按时间排序
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
                String type = articleTypeRepo.getById(id).replaceAll("\n", "")
                if (type == CommonConst.ARTICLE_TYPE_BLOG) {
                    articleInfo.type = "博客"
                } else if (type == CommonConst.ARTICLE_TYPE_NOTE){
                    articleInfo.type = "笔记"
                }
                noteInfoList.add(articleInfo)
            } catch (Exception e) {
            }
        }
        noteInfoList
    }
    //获取所有文章信息
    private List<ArticleListVo.ArticleInfo> getAllArticleInfo() {
        List<String> articleIdList = articleCountRepo.countOnline()
        articleIdList = articleIdList.sort().reverse()
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
                String type = articleTypeRepo.getById(id).replaceAll("\n", "")
                if(type == CommonConst.ARTICLE_TYPE_BLOG) {
                    articleInfo.type = "博客"
                } else if (type == CommonConst.ARTICLE_TYPE_NOTE){
                    articleInfo.type = "笔记"
                }
                articleInfoList.add(articleInfo)
            } catch (Exception e) {
                e.printStackTrace()
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
