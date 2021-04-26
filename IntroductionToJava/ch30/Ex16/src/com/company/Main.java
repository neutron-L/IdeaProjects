package com.company;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    private static int[][] a;
    private static int[][] b;

    public static void main(String[] args) {
	// write your code here

    }

    public static double[][] parallelAddMatrix(double[][] a, double[][] b) {
        RecursiveTask<double[][]> task = new ParallelAddMatrix(0, 0, a.length-1, a[0].length-1);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }

    public static class ParallelAddMatrix extends RecursiveTask<double[][]> {
        private final int l0;
        private final int r0;
        private final int l1;
        private final int r1;
        private static int[][] a;
        private static int[][] b;

        ParallelAddMatrix(int l0, int r0, int l1, int r1) {
            this.l0 = l0;
            this.r0 = r0;
            this.l1 = l1;
            this.r1 = r1;
        }

        @Override
        protected double[][] compute() {
            if (l0 == l1 && r0 == r1) {
                

            } else {
                int lm = (l0 + l1) >> 1;
                int rm = (r0 + r1) >> 1;
            }
        }
    }
}
