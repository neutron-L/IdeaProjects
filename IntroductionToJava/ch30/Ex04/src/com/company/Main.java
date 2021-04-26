package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static Lock lock = new ReentrantLock();
    public static Integer sum = 0;


    public static void main(String[] args) {
        // write your code here
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++)
            executor.execute(new AddSum());
//        System.out.print(sum + " ");

        executor.shutdown();

        while (!executor.isTerminated()) {
        }
        System.out.print(sum + " ");

//        for (int i = 0; i < 1000; i++){
//            Thread thread = new Thread(new AddSum());
//            thread.start();
//        }
//        System.out.print(sum+" ");
//
//        executor.shutdown();
    }

    public static void add() {
        lock.lock();
        sum += 1;
        lock.unlock();
    }

    private static class AddSum implements Runnable {
        @Override
        public void run() {
            add();
        }
    }
}
