package cn.codejavahand.blog.dao

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO 获取文件统计数据
 */
interface IArticleCountRepo {
    int countNote(String status)

    int countBlog(String status)
}