package cn.codejavahand.blog.service

import cn.codejavahand.blog.service.vo.RestRespVo


interface IArticleEditorService {
    /**
     * 更新添加文章
     * @param title
     * @param summary
     * @param type
     * @param classifyLabs
     * @param articleLabs
     * @param content
     * @return
     */
    RestRespVo updateCreate(String id, String title, String summary, String type, String classifyLabs, String articleLabs, String content, String original)

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