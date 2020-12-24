package cn.codejavahand.blog.utils

/**
 * @author heshaojun* @date 2020/12/23
 * @description TODO
 */
class TextFileOpUtils {
    /**
     * 获取文件对象
     * @param fileAbsPath
     * @param create
     * @return
     */
    private static File getFile(String fileAbsPath, boolean create = false) {
        File file = new File(fileAbsPath)
        if (file.exists()) {
            if (!file.isFile()) {
                if (create) {
                    file.createNewFile()
                } else return null
            }
        } else {
            if (create) {
                file.createNewFile()
            } else return null
        }
        file
    }
/**
 *
 * @param fileAbsPath
 * @param content
 * @param append
 * @param create
 */
    static void write(String fileAbsPath, String content, boolean append = false, boolean create = false) {
        FileWriter fileWriter = new FileWriter(getFile(fileAbsPath, create), append)
        fileWriter.withWriter {
            it.write(content)
        }

    }

    /**
     * 读取全部文本文件全部内容
     * @param fileAbsPath
     * @return
     */
    static String readAllString(String fileAbsPath) {
        FileReader fileReader = new FileReader(getFile(fileAbsPath))
        StringBuilder builder = new StringBuilder();
        fileReader.withReader {
            it.readLines().forEach({
                builder.append(it.toString() + "\n")
            })
            builder.toString()
        }
    }
    /**
     *
     * @param fileAbsPath
     * @return
     */
    static List<String> readAllLine(String fileAbsPath) {
        FileReader fileReader = new FileReader(getFile(fileAbsPath))
        StringBuilder builder = new StringBuilder();
        fileReader.withReader {
            it.readLines()
        }
    }

}
