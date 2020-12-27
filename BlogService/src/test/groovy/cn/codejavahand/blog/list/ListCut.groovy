package cn.codejavahand.blog.list

import org.junit.jupiter.api.Test

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/27
 * @Description TODO
 */
class ListCut {
    @Test
    void testMethod() {
        List<Integer> list = new ArrayList<>()
        list.with {
            add 1
            add 2
            add 3
            add 4
            add 5
            add 6
            add 7
        }

        println(list)
        println(list.subList(4,4))
    }
}
