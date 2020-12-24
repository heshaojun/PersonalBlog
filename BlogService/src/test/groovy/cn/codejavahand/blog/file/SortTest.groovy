package cn.codejavahand.blog.file

import org.junit.jupiter.api.Test

/**
 * @author heshaojun* @date 2020/12/24
 * @description TODO
 */
class SortTest {
    @Test
    void test() {
        List<String> list = new ArrayList<>()
        list.with {
            add("1608805886797")
            add("1608805886906")
            add("1608805887562")
            add("1608805887672")
            add("1608805886797")
            add("1608805886609")
        }
        println(list.get(0))
    }
}
