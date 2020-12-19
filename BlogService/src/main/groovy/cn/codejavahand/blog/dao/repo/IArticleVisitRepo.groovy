package cn.codejavahand.blog.dao.repo

interface IArticleVisitRepo {
    IArticleVisitRepo getById(String id)

    void update(String Id, IArticleVisitRepo articleVisitRepo)
}