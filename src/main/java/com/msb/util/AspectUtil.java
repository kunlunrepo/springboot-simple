package com.msb.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-24 14:02
 */
public class AspectUtil {

    private static final Logger log = LoggerFactory.getLogger(AspectUtil.class);

    public final static String ASPECT_TYPE_BEFORE = "前置";

    public final static String ASPECT_TYPE_AFTER = "后置";

    public final static String ASPECT_TYPE_AFTERRETURNING = "返回";

    public final static String ASPECT_TYPE_AFTERTHROWING = "异常";

    public final static String ASPECT_TYPE_AROUND = "环绕";

    /**
     * 显示切面的基本信息
     */
    public static void showAspectBase(String aspectType, JoinPoint joinPoint) {
        showAspectBase(aspectType, joinPoint, null, null);
    }

    /**
     * 显示切面的基本信息
     */
    public static void showAspectBase(String aspectType, JoinPoint joinPoint, Object result) {
        showAspectBase(aspectType, joinPoint, result, null);
    }
    
    /**
     * 显示切面的基本信息
     */
    public static void showAspectBase(String aspectType, JoinPoint joinPoint, Object result, Exception exception) {
        Signature signature = joinPoint.getSignature();
        log.debug("【--------切面*开始--------】");
        log.debug("[切面-类型]: {}", aspectType);
        log.debug("[切面-类名]: {}", signature.getDeclaringTypeName());
        log.debug("[切面-方法名]: {}", signature.getName());
        log.debug("[切面-入参]: {}", joinPoint.getArgs().toString());
        log.debug("[切面-出参]: {}", result);
        String exMsg = null == exception? "无异常" : exception.getMessage();
        log.debug("[切面-异常]: {}", exMsg);
        log.debug("【--------切面*结束--------】");
    }
}
