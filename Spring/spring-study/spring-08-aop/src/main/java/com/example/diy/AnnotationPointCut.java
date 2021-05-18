package com.example.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

//方式三 注解
@Aspect
public class AnnotationPointCut {
    @Before("execution(* com.example.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("====before execute=======");
    }

    @After("execution(* com.example.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("====after execute=======");
    }

    @Around("execution(* com.example.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("======before around====");
        jp.proceed();
        System.out.println("======after around====");
    }
}
