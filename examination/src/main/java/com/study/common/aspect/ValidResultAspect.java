package com.study.common.aspect;

import com.study.common.bean.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 参数验证结果处理切面
 */
@Aspect
@Component
@Order(3)
public class ValidResultAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidResultAspect.class);

    @Pointcut("execution(public * com.study.controller.*.*(..))")
    public void validResult() {
    }

    @Around("validResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                // 可以拿到接口中的BindingResult，对BindingResult结果进行处理
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    //获取所有的异常信息
                    List<ObjectError> allErrors = result.getAllErrors();
                    for (ObjectError error : allErrors) {
                        return CommonResult.failed(error.getDefaultMessage());
                    }
                }
            }
        }
        Object result = joinPoint.proceed();
        return result;
    }
}
