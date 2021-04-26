package com.company;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Main {
    private static int[] list;
    public static void main(String[] args) {
	// write your code here
        final int N = 90;
        list = new int[N];
        for (int i = 0; i < N; i++)
            list[i] = (int)(Math.random()*100);
        parallelQuickSort();
        for (int i = 0; i < N; i++)
            System.out.print(list[i] + " ");
    }

    public static void parallelQuickSort() {
        RecursiveAction task = new ParallelQuickSort(0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
    }

    public static class ParallelQuickSort extends RecursiveAction {
        private final int THRESHOLD = 100;
        private int low;
        private int high;

        ParallelQuickSort(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low < high - 1) {
                int i = low - 1;
                int j = low;
                int x = list[high - 1];
                while (j < high - 1) {
                    if (list[j] <= x) {
                        i++;
                        int temp = list[j];
                        list[j] = list[i];
                        list[i] = temp;
                    }
                    j++;
                }
                i++;
                int temp = list[high - 1];
                list[high - 1] = list[i];
                list[i] = temp;
                invokeAll(new ParallelQuickSort(low, i), new ParallelQuickSort(i, high));
            }
        }
    }
}
