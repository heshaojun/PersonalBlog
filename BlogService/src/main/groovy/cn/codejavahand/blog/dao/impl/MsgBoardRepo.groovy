package cn.codejavahand.blog.dao.impl

import cn.codejavahand.blog.common.CommonConst
import cn.codejavahand.blog.config.SysConfig
import cn.codejavahand.blog.dao.IMsgBoardRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MsgBoardRepo implements IMsgBoardRepo {
    @Autowired
    private SysConfig sysConfig

    @Override
    @Cacheable(value = "AllMsgBoardId")
    List<String> getAllMsgId() {
        File file = new File("${sysConfig.rootPath}/${CommonConst.MSG_BOARD_PATH}")
        List<String> list = new ArrayList<>()
        if (file.exists() && file.isDirectory()) {
            File[] children = file.listFiles({ File f -> f.name.endsWith(".txt") } as FileFilter)
            for (File f in children) {
                list.add f.name
            }
        }
        list
    }

    @Override
    @CacheEvict(value = "AllMsgBoardId")
    void addMsg(String msgId, String msg) {

    }
}
