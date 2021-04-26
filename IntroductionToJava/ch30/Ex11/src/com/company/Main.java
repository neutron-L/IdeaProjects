package com.company;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static String str1="abc";
    private static String str2="efg";

    public static void main(String[] args) {
	// write your code here
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DeadLock1());
        executor.execute(new DeadLock2());
        executor.shutdown();

    }

    private static class DeadLock1 implements Runnable {

        @Override
        public void run() {
            synchronized (str1){
                System.out.println("DeadLock1 has locked str1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("now DeadLock1 wait to locked str2...");
                synchronized (str2) {
                    System.out.println("DeadLock1 has locked str2...");
                }
            }
        }
    }

    private static class DeadLock2 implements Runnable {

        @Override
        public void run() {
            synchronized (str2){
                System.out.println("DeadLock2 has locked str2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("now DeadLock2 wait to locked str1...");
                synchronized (str1) {
                    System.out.println("DeadLock2 has locked str1...");
                }
            }
        }
    }


}
