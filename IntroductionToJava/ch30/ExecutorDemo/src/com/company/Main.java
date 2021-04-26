package com.company;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ExecutorService executor = Executors.newFixedThreadPool(1);

        executor.execute(new PrintChar('a', 100));
        executor.execute(new PrintChar('b', 100));
        executor.execute(new PrintNum(100));
        executor.execute(new PrintChar('c', 100));

        executor.shutdown();
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