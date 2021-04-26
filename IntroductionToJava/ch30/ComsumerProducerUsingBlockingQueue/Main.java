package com.company;

import java.util.concurrent.*;

public class Main {
    private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(2);

    public static void main(String[] args) {
	// write your code here
        System.out.println("nennene");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new ProducerTask());
        executorService.execute(new ConsumerTask());

        executorService.shutdown();
        System.out.println("nennene");
    }
    private static class ProducerTask implements Runnable {
        @Override
        public void run() {
            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes " + i);
                    buffer.put(i++);
                    Thread.sleep((int)(Math.random() * 10000));
                }
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    private static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\tConsumer reads " + buffer.take());
                    Thread.sleep((int) (Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

//    private static class Buffer {
//        private static final int CAPACITY = 1;
//        private java.util.LinkedList<Integer> queue = new java.util.LinkedList<>();
//
//        private static Lock lock = new ReentrantLock();
//        private static Condition notEmpty = lock.newCondition();
//        private static Condition notFull = lock.newCondition();
//
//        public void write(int value) {
//            lock.lock();
//            try {
//                while (queue.size() == CAPACITY) {
//                    System.out.println("Wait for notFull condition");
//                    notFull.await();
//                }
//                queue.offer(value);
//                notEmpty.signal();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            finally {
//                lock.unlock();
//            }
//        }
//
//        public int read() {
//            int value = 0;
//            lock.lock();
//            try {
//                while (queue.size() == 0) {
//                    System.out.println("\t\tWait for notEmpty condition");
//                    notEmpty.await();
//                }
//                value = queue.remove();
//                notFull.signal();
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//            return value;
//        }
//    }
}
