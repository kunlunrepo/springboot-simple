package com.msb.aspect;

import com.msb.util.AspectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * description :
 * @author kunlunrepo
 * date :  2023-05-17 15:59
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    // 公共切点
    @Pointcut("execution(* com.msb.controller.AspectController.log(..))")
    public void serviceStartFlowSyncPointCut(){}

    // 前置通知
    @Before("serviceStartFlowSyncPointCut()")
    public void startFlowBefore(JoinPoint joinPoint) {
        AspectUtil.showAspectBase(AspectUtil.ASPECT_TYPE_BEFORE, joinPoint);
        log.info("【simple-log-前置通知】--- {}", joinPoint);
    }

    // 后置通知 无论切点是否异常都会执行
    @After("serviceStartFlowSyncPointCut()")
    public void startFlowAfter(JoinPoint joinPoint) {
        AspectUtil.showAspectBase(AspectUtil.ASPECT_TYPE_AFTER, joinPoint);
        log.info("【simple-log-后置通知】--- {}", joinPoint);
    }

    // 返回通知 切点正常返回后执行
    @AfterReturning(value = "serviceStartFlowSyncPointCut()", returning = "result")
    public void startFlowAfterReturning(JoinPoint joinPoint, Object result) {
        AspectUtil.showAspectBase(AspectUtil.ASPECT_TYPE_AFTERRETURNING, joinPoint, result);
        log.info("【simple-log-返回通知】--- {}", joinPoint);
    }


    // 环绕通知 操作切点方法是否执行
    @Around("serviceStartFlowSyncPointCut()")
    public Object startFlowAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        AspectUtil.showAspectBase(AspectUtil.ASPECT_TYPE_AROUND, proceedingJoinPoint);
        Object proceed = proceedingJoinPoint.proceed();
        AspectUtil.showAspectBase(AspectUtil.ASPECT_TYPE_AROUND, proceedingJoinPoint);
        log.info("【simple-log-环绕通知】--- {}", proceedingJoinPoint);
        return proceed;
    }

    // 异常通知 切点出现异常时运行
    @AfterThrowing(value = "serviceStartFlowSyncPointCut()", throwing = "exception")
    public void startFlowAfterThrowing(JoinPoint joinPoint, Exception exception) {
        AspectUtil.showAspectBase(AspectUtil.ASPECT_TYPE_AFTERTHROWING, joinPoint, null, exception);
        log.info("【simple-log-异常通知】--- {}", joinPoint);
    }

}
