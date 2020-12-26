package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IArticleCommentRepo
import cn.codejavahand.blog.service.vo.CommentVo
import cn.codejavahand.blog.utils.TextFileOpUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.apache.commons.codec.binary.Base64

@Service
class ArticleCommentRepo implements IArticleCommentRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    void addComment(String id, String commentId, CommentVo commentVo) {
        String data = Base64.encodeBase64String(commentVo.name.getBytes()) + "\n" + Base64.encodeBase64String(commentVo.comment.getBytes())
        TextFileOpUtils.write "${sysConfig.rootPath}/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.ARTICLE_COMMENTS_PATH}/$commentId", data, false, true
    }

    @Override
    List<CommentVo> getAllId(String id) {
        File file = new File("${sysConfig.rootPath}/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.ARTICLE_COMMENTS_PATH}")
        List<CommonConst> list = new ArrayList<>()
        List<String> commentIdList = new ArrayList<>()
        if (file.exists() && file.isDirectory()) {
            String[] commentIds = file.list()
            commentIdList.addAll(commentIds)
        }
        commentIdList.sort()
        for (String cid in commentIdList) {
            list.add(getById(id, cid))
        }
        list
    }

    @Override
    void deleteComment(String id, String commentId) {
        File file = new File("${sysConfig.rootPath}/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.ARTICLE_COMMENTS_PATH}/$commentId")
        file.delete()
    }

    @Override
    CommentVo getById(String id, String commentId) {
        List<String> dataList = TextFileOpUtils.readAllLine "${sysConfig.rootPath}/${CommonConst.ARTICLE_PATH}/$id/${CommonConst.ARTICLE_COMMENTS_PATH}/$commentId"
        CommentVo commentVo = new CommentVo()
        commentVo.name = dataList.get(0)
        commentVo.comment = dataList.get(1)
        commentVo
    }
}
