package cn.codejavahand.blog.service

import cn.codejavahand.blog.service.vo.RestRespVo


interface IArticleEditorService {
    /**
     * 添加文章
     * @param title
     * @param summary
     * @param type
     * @param classifyLabs
     * @param articleLabs
     * @param content
     * @return
     */
    RestRespVo addArticle(String id, String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original)

    /**
     * 更新文章
     * @param id
     * @param title
     * @param summary
     * @param classifyLabs
     * @param articleLabs
     * @param content
     * @return
     */
    RestRespVo updateArticle(String id, String title, String summary, String classifyLabs, String articleLabs, String content)

    /**
     * 上线文章
     * @param id
     * @return
     */
    RestRespVo upLine(String id)

    /**
     * 下线文章
     * @param id
     * @return
     */
    RestRespVo offLine(String id)
}