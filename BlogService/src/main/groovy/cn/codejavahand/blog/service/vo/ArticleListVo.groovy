package cn.codejavahand.blog.service.vo

/**
 * @author heshaojun* @date 2020/12/17
 * @description TODO
 */
class ArticleListVo {
    int pageSize //单个页面list数量
    int currentPage //当前数据页码
    int listCount //当前文章数
    String orderBy //排列方式
    int totalPage //总页面数
    int articleCount //总文章数
    int blogCount//博客数
    int noteCount //笔记数
    List<ArticleInfo> list //文章信息列表

    static class ArticleInfo {
        int visits //访问数
        int comments //评论数
        String createTime //创建时
        String type //文章类型
        String title//文章标题
        String summary//文章摘要
        String id //文章id
    }

}
