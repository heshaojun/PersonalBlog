package cn.codejavahand.blog.dao.entity

/**
 * @author heshaojun* @date 2020/12/17
 * @description 文章详情实体
 */
class ArticleDetailDo {
    String id //文章id
    String title//文章标题
    String summary//文章摘要
    String original//原创标示
    String content//文章内容
    String status//文章状态 online已经发布，offline未发布
    String type//文章类型 blog博客 note笔记
    String classify//文章分类标签
    String labs//文章标签
    ArticleDetailDo() {
    }

    ArticleDetailDo(String id, String title, String summary, String original, String content, String status, String type, String classify, String labs) {
        this.id = id
        this.title = title
        this.summary = summary
        this.original = original
        this.content = content
        this.status = status
        this.type = type
        this.classify = classify
        this.labs = labs
    }
}
