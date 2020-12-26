package cn.codejavahand.blog.service.vo

class WebStatisticVo {
    long visits
    long articles
    long blogs
    long notes
    long comments

    WebStatisticVo() {
    }

    WebStatisticVo(long visits, long articles, long blogs, long notes, long comments) {
        this.visits = visits
        this.articles = articles
        this.blogs = blogs
        this.notes = notes
        this.comments = comments
    }
}
