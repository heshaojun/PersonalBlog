package cn.codejavahand.blog.dao.repo

interface IArticleVisitRepo {
    int getById(String id)

    int addOne(String id)
}