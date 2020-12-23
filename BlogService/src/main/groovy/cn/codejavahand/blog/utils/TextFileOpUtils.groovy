package cn.codejavahand.blog.utils

/**
 * @author heshaojun* @date 2020/12/23
 * @description TODO
 */
class TextFileOpUtils {
    /**
     * 获取文件对象
     * @param filePath
     * @param create
     * @return
     */
    private static File getFile(String filePath, boolean create = false) {
        File file = new File(filePath)
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

    static void appendContent(String filePath, String content) {
        FileWriter fileWriter = new FileWriter(getFile(filePath, true), true)
        fileWriter.withWriter {
            it.write(content)
        }

    }

    static void coverContent(String filePath, String content) {
        FileWriter fileWriter = new FileWriter(getFile(filePath, false))
        fileWriter.withWriter {
            it.write(content)
        }
    }

    static String readAll(String filePath) {
        FileReader fileReader = new FileReader(getFile(filePath))
        StringBuilder builder = new StringBuilder();
        fileReader.withReader {
            it.readLines().forEach({
                builder.append(it.toString() + "\n")
            })
            builder.toString()
        }
    }

}
