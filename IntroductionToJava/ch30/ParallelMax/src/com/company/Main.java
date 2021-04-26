package com.company;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static int[] list;


    public static void main(String[] args) {
	// write your code here
        final int N = 9000000;
        list = new int[N];
        for (int i = 0; i < list.length; i++)
            list[i] = i;
//            list[i] = (int)(Math.random() * 1000000);
        long startTime = System.currentTimeMillis();
        System.out.println("\nThe maximal number is " + max());
        long endTime = System.currentTimeMillis();
        System.out.println("Time is " + (endTime - startTime) + " milliseconds");


    }

    public static int max() {
        RecursiveTask<Integer> task = new MaxTask(0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    private static class MaxTask extends RecursiveTask<Integer> {
        private final static int THRESHOLD = 1000;
        private int low;
        private int high;

        MaxTask(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            int max = list[low];
            if (high - low < THRESHOLD) {
                max = list[low];
                for (int i = low + 1; i < high; i++)
                    max = Math.max(max, list[i]);
                return max;
            } else {
                int mid = (low + high) >> 1;
                RecursiveTask<Integer> left = new MaxTask(low, mid);
                RecursiveTask<Integer> right = new MaxTask(mid, high);

                left.fork();
                right.fork();
                return Math.max(left.join(), right.join());
            }
        }
    }

}
