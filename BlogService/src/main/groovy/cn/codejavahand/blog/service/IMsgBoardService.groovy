package cn.codejavahand.blog.service

import cn.codejavahand.blog.service.vo.RestRespVo

/**
 * @author heshaojun* @date 2020/12/29
 * @description TODO
 */
interface IMsgBoardService {
    RestRespVo getTheNewBord(int top)

    RestRespVo get
}