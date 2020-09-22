package com.zhu.demo.common;

import com.zhu.demo.entity.MyLog;
import com.zhu.demo.entity.SysLog;
import com.zhu.demo.entity.UserEntity;
import com.zhu.demo.service.SysLogService;
import com.zhu.demo.utils.IpAdrressUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Author: zhutao
 * @Date: 2020/9/22 16:32
 */
@Component
@Aspect
public class SysLogAspect {
    @Resource
    private SysLogService sysLogService;

    /**
     * 定义切点 @Pointcut
     * 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.zhu.demo.entity.MyLog)")
    public void logPointCut() {

    }

    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("进入切面...");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        SysLog sysLog = new SysLog();

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 保存获取的操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if(myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);
        }

        // 保存请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        // 获取请求的参数,将参数所在的数组转换成json
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            if (args[i] instanceof Integer || args[i] instanceof String) {
                arguments[i] = args[i];
            }
            continue;
        }
        String params = "";
        if (arguments != null) {
            try {
                // 将arguments转换为json
                params = "";
            } catch (Exception e) {
                params = "无特殊参数";
            }
        }
        sysLog.setParams(params);

        //获取用户名
        UserEntity user = (UserEntity)session.getAttribute("Constants.LOGIN_KEY");
        sysLog.setUserName("userName");

        //获取用户ip地址
        sysLog.setIp(IpAdrressUtil.getIPAddress(request));

        //设置时间
        sysLog.setCreateTime(LocalDateTime.now());

        //调用service保存SysLog实体类到数据库
        sysLogService.save(sysLog);
    }
}