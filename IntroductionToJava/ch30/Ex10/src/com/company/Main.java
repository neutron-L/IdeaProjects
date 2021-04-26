package com.company;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static AccessAndModifyHashSet accessAndModifyHashSet = new AccessAndModifyHashSet();
    private static HashSet<Integer> hashSet = new HashSet<>();

    public static void main(String[] args) {
        // write your code here
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ModifySet());
        executor.execute(new AccessSet());
        executor.shutdown();
    }

    private static class AccessSet implements Runnable {
        @Override
        public void run() {
            while (true) {
                accessAndModifyHashSet.accessHashSet();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ModifySet implements Runnable {
        @Override
        public void run() {
            while (true) {
                accessAndModifyHashSet.modifyHashSet();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class AccessAndModifyHashSet {
        private final Lock lock = new ReentrantLock();
        public void modifyHashSet() {
            lock.lock();
            try {
//                System.out.print("88");
                hashSet.add((int) (Math.random() * 10));
            } finally {
                lock.unlock();
            }
        }

        public synchronized void accessHashSet() {
            lock.lock();
            try {
//                System.out.print("&&");
                Iterator<Integer> iter = hashSet.iterator();
                while (iter.hasNext())
                    System.out.print(iter.next() + " ");
                System.out.println();
                Thread.sleep(1000);
                iter = hashSet.iterator();
                while (iter.hasNext())
                    System.out.print(iter.next() + " ");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }
}
