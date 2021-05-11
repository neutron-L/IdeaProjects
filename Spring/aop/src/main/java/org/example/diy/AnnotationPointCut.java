package org.example.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//使用注解方式实现aop
//标注这是个切面

@Aspect
public class AnnotationPointCut {
    @Before("execution(* org.example.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("===== before method execute ======");
    }

    @After("execution(* org.example.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("===== after method execute ======");
    }

    //在环绕增强中,我们可以给定一个参数代表我们要获取处理切入的点
    @Around("execution(* org.example.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===before around===");
        //执行方法
        Object process = joinPoint.proceed();

        System.out.println("===after around===");
    }
}
