package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.dao.IArticleCountRepo
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

/**
 * @author heshaojun* @date 2020/12/25
 * @description TODO
 */
@Service
class ArticleCountRepo implements IArticleCountRepo {
    @Override
    @Cacheable(value = "countNote", key = "#status")
    int countNote(String status) {
        return 0
    }

    @Override
    @Cacheable(value = "countBlog", key = "#status")
    int countBlog(String status) {
        return 0
    }
}
