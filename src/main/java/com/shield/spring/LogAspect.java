package com.shield.spring;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

import com.shield.frame.utils.JsonUtil;

/**
 * springmvc框架中的自动日志类
 */
@Aspect
public class LogAspect implements Ordered {

    static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    private int order = 1;

    /**
     * 注解实现serviceimpl日志输出 (仅限public方法)
     *
     * @param pjp
     * @param autoLog
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.shield.frame.*.serviceimpl.*.*(..)) && @annotation(autoLog)")
    public Object serviceLog(ProceedingJoinPoint pjp, AutoLog autoLog) throws Throwable {
        String str = "";

        if (log.isInfoEnabled()) {
            str = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "] ["
                + autoLog.desc();

            ArrayList<Object> aList = new ArrayList<Object>();

            for (Object obj : pjp.getArgs()) {
                if (obj != null
                    && (obj.getClass().getName().startsWith("java.lang") || obj.getClass()
                        .getName().startsWith("com.shield"))) {
                    aList.add(obj);
                }
            }

            // 被拦截方法前的日志 位置
            log.info(str + "] [开始] [参数：" + JsonUtil.writeValueAsString(aList));
        }

        Object retVal = pjp.proceed();

        if (log.isInfoEnabled()) {
            // 被拦截方法后的日志 位置
            log.info(str + "] [结束]");
            // log.info(str+ "] [结束]
            // [返回值："+JsonUtil.writeValueAsString(retVal));
        }

        return retVal;
    }

    /**
     * 注解实现controller日志输出 (仅限public方法)
     *
     * @param pjp
     * @param autoLog
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.shield.frame.*.controller.*.*(..)) && @annotation(autoLog)")
    public Object controLog(ProceedingJoinPoint pjp, AutoLog autoLog) throws Throwable {
        String str = "";

        if (log.isInfoEnabled()) {
            str = pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + "] ["
                + autoLog.desc();

            ArrayList<Object> aList = new ArrayList<Object>();

            for (Object obj : pjp.getArgs()) {
                if (obj != null
                    && (obj.getClass().getName().startsWith("java.lang") || obj.getClass()
                        .getName().startsWith("com.shield"))) {
                    aList.add(obj);
                }
            }

            // 被拦截方法的前日志 位置
            log.info(str + "] [开始] [参数：" + JsonUtil.writeValueAsString(aList));
        }

        Object retVal = pjp.proceed();

        if (log.isInfoEnabled()) {
            // 被拦截方法的后日志 位置
            log.info(str + "] [结束]");
            // log.info(str+ "] [结束]
            // [返回值："+JsonUtil.writeValueAsString(retVal));
        }

        return retVal;
    }

    /**
     * 方法执行前 (仅限public方法)
     *
     * @param jp
     */
    public void doBefore(JoinPoint jp) {
        if (log.isInfoEnabled()) {
            String strLog = jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
            ArrayList<Object> aList = new ArrayList<Object>();

            for (Object obj : jp.getArgs()) {
                if (obj != null
                    && (obj.getClass().getName().startsWith("java.lang") || obj.getClass()
                        .getName().startsWith("com.shield"))) {
                    aList.add(obj);
                }
            }

            log.info(strLog + "] [开始] [参数：" + JsonUtil.writeValueAsString(aList));
        }
    }

    /**
     * 方法正常执行结束时 (仅限public方法)
     *
     * @param rtnVal
     *            方法返回的内容
     */
    public void doAfterReturn(JoinPoint jp, Object rtnVal) {
        if (log.isInfoEnabled()) {
            String strLog = jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
            log.info(strLog + "] [结束]");
            // log.info(strLog+ "] [结束]
            // [返回值："+JsonUtil.writeValueAsString(rtnVal));
        }
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }

}
