package cn.codejavahand.blog.file


import org.junit.jupiter.api.Test

/**
 * @author heshaojun* @date 2020/12/23
 * @description TODO
 */
class FileUtilsTest {
    private String filePath = "E:\\testFolder\\file\\test.txt"



    @Test
    void test01() {
        File pathFile = new File("E:\\testFolder\\file\\1608792394326")
        File[] files = pathFile.listFiles({ File file -> file.name.endsWith(".txt") } as FileFilter)
        for (File file in files) {
            println(file.name)
        }
    }
}
