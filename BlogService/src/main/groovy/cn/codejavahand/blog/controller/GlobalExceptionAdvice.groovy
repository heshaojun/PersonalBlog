package cn.codejavahand.blog.controller

import cn.codejavahand.blog.service.vo.RestRespVo
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    RestRespVo defaultHandler(Exception e) {
        RestRespVo vo = new RestRespVo()
        vo.code = 500
        vo.msg = "系统异常"
        vo.result = "fail"
        vo
    }
}
