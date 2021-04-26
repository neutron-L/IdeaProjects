package com.company;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // write your code here
        Runnable printA = new PrintChar('a', 100);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(100);

//        Thread thread1 = new Thread(printA);
//        Thread thread2 = new Thread(printB);
//        Thread thread3 = new Thread(print100);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(printA);
        executor.execute(printB);
        executor.execute(print100);

        executor.shutdown();

//        thread1.start();
//        thread2.start();
//        thread3.start();
    }
}

class PrintChar implements Runnable{
    private char charToPrint;
    private int times;

    public PrintChar(char c, int t) {
        charToPrint = c;
        times = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++)
            System.out.print(charToPrint);
    }
}

class PrintNum implements Runnable{
    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    @Override
    public void run() {
        for (int i = 1; i <= lastNum; i++)
            System.out.print(" " + i);
    }
}