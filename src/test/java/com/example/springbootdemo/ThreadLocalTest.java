package com.example.springbootdemo;


import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {

        ThreadLocal threadLocal = new ThreadLocal();
        //开启两个线程 测试是否冲突

        new Thread(() -> {
            threadLocal.set("这是第一个线程！！");
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
        },"demo1").start();

        new Thread(() -> {
            threadLocal.set("这是第二个线程！！");
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
        },"demo2").start();
    }
}
