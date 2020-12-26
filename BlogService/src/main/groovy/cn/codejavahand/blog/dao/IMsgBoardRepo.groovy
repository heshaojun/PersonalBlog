package cn.codejavahand.blog.dao

/**
 * @Author shaojun he
 * @Mail keepword_heshaojun@hotmail.com
 * @Date 2020/12/26
 * @Description TODO 留言板数据存取操作接口
 */
interface IMsgBoardRepo {
    List<String> getAllMsgId()

    void addMsg(String msgId, String msg)
}