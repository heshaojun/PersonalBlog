package cn.codejavahand.blog.file

import cn.codejavahand.blog.utils.TextFileOpUtils
import org.junit.jupiter.api.Test

/**
 * @author heshaojun* @date 2020/12/23
 * @description TODO
 */
class FileUtilsTest {
    private String filePath = "E:\\testFolder\\file\\test.txt"

    @Test
    void appendTest() {
        println TextFileOpUtils.readAll(filePath)
    }
}
