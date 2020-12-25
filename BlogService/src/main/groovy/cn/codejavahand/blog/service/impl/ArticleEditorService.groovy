package cn.codejavahand.blog.service.impl

import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.service.IArticleEditorService
import cn.codejavahand.blog.service.vo.RestRespVo
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleEditorService implements IArticleEditorService {
    private final Log logger = LogFactory.getLog(ArticleEditorService.getClass())
    @Autowired
    private SysConfig sysConfig


    @Override
    RestRespVo addArticle(String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original) {
        logger.info "开始添加文章"
        RestRespVo respVo = new RestRespVo()
        respVo.setCode(500)
        respVo.setResult("fail")
        if (title == null || title == "" || summary.replaceAll(" ", '') == "") {
            respVo.setMsg("文章标题为空")
            logger.info "文章标题为空"
        } else if (type != 'note' && type != 'blog') {
            respVo.setMsg('文章类型异常')
            logger.info "文章类型不存在"
        } else if (summary == null || summary == "" || summary.replaceAll(" ", "") == "") {
            respVo.setMsg("文章摘要为空")
            logger.info "文章摘要为空"
        } else if (classifyLabs == null || classifyLabs == "" || classifyLabs.replaceAll(" ", "") == "") {
            respVo.setMsg("分类标签为空")
            logger.info "分类标签为空"
        } else if (articleLabs == null || articleLabs == "" || articleLabs.replaceAll(" ", "") == "") {
            respVo.setMsg("文章标签为空")
            logger.info "文章标签为空"
        } else if (original != 'yes' && original != 'no') {
            respVo.setMsg("原创标示异常")
            logger.info "原则标志为空"
        } else if (content == null || content == "" || content.replaceAll(" ", "") == "") {
            respVo.setMsg("文章内容为空")
            logger.info "文章内容为空"
        } else {
            String id = new Date().getTime().toString()
            File file = new File(sysConfig.getRootPath() + "/" + id)
            while (true) {
                if (file.exists()) {
                    id = new Date().getTime().toString()
                    file = new File(sysConfig.getRootPath() + "/" + id)
                } else {
                    file.mkdirs()
                    break
                }
            }
            try {
                respVo.setCode(200)
                respVo.setResult('success')
                respVo.setMsg('ok')
                logger.info "添加文章成功"
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
        respVo
    }

    @Override
    RestRespVo updateArticle(String id, String title, String summary, String classifyLabs, String articleLabs, String content) {
        return null
    }

    @Override
    RestRespVo upLine(String id) {
        return null
    }

    @Override
    RestRespVo offLine(String id) {
        return null
    }
}
