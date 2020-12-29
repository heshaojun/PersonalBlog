package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IMsgBoardRepo
import cn.codejavahand.blog.service.vo.CommentVo
import cn.codejavahand.blog.service.vo.MsgBoardVo
import cn.codejavahand.blog.utils.TextFileOpUtils
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MsgBoardRepo implements IMsgBoardRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    List<String> getAllMsgId() {
        File file = new File("${sysConfig.rootPath}/${CommonConst.MSG_BOARD_PATH}")
        List<String> list = new ArrayList<>()
        if (file.exists() && file.isDirectory()) {
            File[] children = file.listFiles()
            for (File f in children) {
                list.add f.name
            }
        }
        list
    }

    @Override
    @CachePut(value = "msgBoard", key = "#msgId")
    void addMsg(String msgId, MsgBoardVo msgBoardVo) {
        String data = Base64.encodeBase64String(msgBoardVo.name.getBytes()) + "\n" + Base64.encodeBase64String(msgBoardVo.msg.getBytes())
        TextFileOpUtils.write "${sysConfig.rootPath}/${CommonConst.MSG_BOARD_PATH}/${msgId}", data, false, true
    }

    @Override
    void deleteById(String msgId) {
        new File("${sysConfig.rootPath}/${CommonConst.MSG_BOARD_PATH}/$msgId").delete()
    }

    @Override
    MsgBoardVo getById(String msgId) {
        List<String> dataList = TextFileOpUtils.readAllLine "${sysConfig.rootPath}/${CommonConst.MSG_BOARD_PATH}/${msgId}"
        MsgBoardVo msgBoardVo1 = new MsgBoardVo()
        msgBoardVo1.msgId = msgId
        msgBoardVo1.name = dataList.get(0)
        msgBoardVo1.msg = dataList.get(1)
        msgBoardVo1
    }
}
