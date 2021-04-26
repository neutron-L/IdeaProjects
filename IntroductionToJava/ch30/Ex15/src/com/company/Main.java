package com.company;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Main {
private static double[] list;
    public static void main(String[] args) {
	// write your code here
        final int N = 100;
        list = new double[N];
        for(int i = 0; i < N; i++)
            list[i] = i+1;
        System.out.println("The sum of list is " + parallelSum());
    }
    private static double parallelSum() {
        RecursiveTask<Double> task = new ParallelSum(0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    private static class ParallelSum extends RecursiveTask<Double> {
        private int low;
        private int high;

        ParallelSum(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        protected Double compute() {
            if (low < high - 1) {
                int mid = (low + high) >> 1;
                RecursiveTask<Double> left = new ParallelSum(low, mid);
                RecursiveTask<Double> right = new ParallelSum(mid, high);
                left.fork();
                right.fork();
                return left.join().doubleValue() + right.join().doubleValue();

            } else return list[low];
        }
    }
}
