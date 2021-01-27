package com.nb6868.onexboot.api.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 访问控制，切面处理类
 *
 * @author Charles zhangchaoxu@gmail.com
 */
// @Aspect
@Component
public class AccessControlAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" )
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        /*if (SecurityUser.isAnon()) {
            // 注解所在方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
            AccessControl annotation = method.getAnnotation(AccessControl.class);
            if (annotation == null || !annotation.anon()) {
                // 不存在注解或者注解不允许
                throw new OnexException(ErrorCode.UNAUTHORIZED);
            }
        }*/

        return joinPoint.proceed();
    }

}
