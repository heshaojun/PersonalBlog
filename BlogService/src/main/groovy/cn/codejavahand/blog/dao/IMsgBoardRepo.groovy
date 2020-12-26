package cn.codejavahand.blog.dao

interface IMsgBoardRepo {
    List<String> getAllMsgId()

    void addMsg(String msgId, String msg)
}