package cn.codejavahand.blog.file

import cn.codejavahand.blog.dao.entity.ArticleDetailDo
import cn.codejavahand.blog.dao.repo.IArticleDetailRepo
import cn.codejavahand.blog.dao.repo.impl.ArticleDetailRepo
import cn.codejavahand.blog.utils.TextFileOpUtils
import org.junit.jupiter.api.Test

/**
 * @author heshaojun* @date 2020/12/23
 * @description TODO
 */
class FileUtilsTest {
    private String filePath = "E:\\testFolder\\file\\test.txt"

    /*@Test
    void appendTest() {
        IArticleDetailRepo detailRepo = new ArticleDetailRepo();
        ArticleDetailDo detailDo = new ArticleDetailDo("123345", "问老师的房间看电视了就", "大撒刚打开拉萨附近的就覅偶尔发点美女", "原创",
                "dfdsafdsafecve", "online", "note", "java python", "adf dfee dad")
        detailRepo.addArticle(detailDo.id, detailDo)
    }*/

    @Test
    void test01() {
        File pathFile = new File("E:\\testFolder\\file\\1608792394326")
        File[] files = pathFile.listFiles({ File file -> file.name.endsWith(".txt") } as FileFilter)
        for (File file in files) {
            println(file.name)
        }
    }
}
