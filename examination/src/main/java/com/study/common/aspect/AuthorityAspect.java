package com.study.common.aspect;

import com.study.bean.User;
import com.study.common.annotation.NoLogin;
import com.study.common.bean.CommonResult;
import com.study.common.util.JWTUtil;
import com.study.common.util.UserContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 权限校验切面
 */
@Aspect
@Component
@Order(2)
public class AuthorityAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityAspect.class);

    @Pointcut("execution(public * com.study.controller.*.*(..))")
    public void authority() {
    }

    @Around("authority()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //查看方法是否有免登录注解，没有免登录注解需要鉴权
        NoLogin methodNoLogin = method.getAnnotation(NoLogin.class);
        if (methodNoLogin == null) {
            String token = request.getHeader("Authorization");
            if (token == null || token.equals("") || !JWTUtil.verify(token)) {
                return CommonResult.unauthorized(null);
            }
            User user = JWTUtil.getUser(token);
            UserContextHolder.setInstance(user);//将user放入threadLocal
        }
        Object result = joinPoint.proceed();
        UserContextHolder.removeInstance();//将user移除threadLocal，防止内存泄漏
        return result;
    }
}