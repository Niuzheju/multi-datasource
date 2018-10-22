package com.niuzj.aop;

import com.niuzj.model.DataSourceManage;
import com.niuzj.model.DataSourceName;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    @Around("execution(* com.niuzj.service.*.*(..))")
    public Object changeDataSource(ProceedingJoinPoint proceed) throws Throwable {
        Signature signature = proceed.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new RuntimeException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        DataSourceName name = null;
        Method method = proceed.getTarget().getClass().getMethod(signature.getName(), methodSignature.getParameterTypes());
        name = method.getAnnotation(DataSourceName.class);
        if (name != null) {
            DataSourceManage.setDataSource(name.name().getType());
        }
        Object obj = proceed.proceed();
        DataSourceManage.clear();
        return obj;
    }
}
