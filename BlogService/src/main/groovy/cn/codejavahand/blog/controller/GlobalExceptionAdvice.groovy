package cn.codejavahand.blog.controller

import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    RestRespVo defaultHandler(Exception e) {
        RestRespVo respVo = new RestRespVo()
        respVo.code = 500
        respVo.msg = "系统异常"
        respVo.result = "fail"
        respVo
    }
}
