package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO  获取文件统计数据
 */
interface IArticleCountRepo {
    List<String> countNote(String status)

    List<String> countBlog(String status)

    List<String> countAll()

    List<String> countOnline()

    List<String> countOffLine()
}