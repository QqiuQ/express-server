package com.team24.express.controller.exception;

import com.team24.express.common.AjaxResult;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 用于接口参数校验处理的控制器
 */
@Slf4j
//@RestControllerAdvice
public class ValidationController {

    /**
     * 与SpringBoot保持一致，校验不通过打印警告信息，而不是直接抛出异常
     *
     * @param exception 验证异常
     * @return 校验结果
     */
    @ExceptionHandler(ValidationException.class)
    public AjaxResult validateError(ValidationException exception) {
        log.warn("Resolved [{}: {}]", exception.getClass().getName(), exception.getMessage());
        return AjaxResult.error(AjaxResult.HttpStatus.ERROR, "请求参数有错误");
    }
}
