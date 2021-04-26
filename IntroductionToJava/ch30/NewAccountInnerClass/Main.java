package com.company;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.*;

public class Main {

    private static Account account = new Account();
    public static void main(String[] args) {
	// write your code here
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++)
            executor.execute(new AddPennyTask());
        executor.shutdown();
        while ((!executor.isTerminated())) {
        }
        System.out.print("What is balance?" + account.getBalance());
    }

    private static class AddPennyTask implements Runnable {
        public void run(){
            account.deposit(1);
        }
    }

    private class Account {
        private static Semaphore semaphore = new Semaphore(1);
        private int balance = 0;

        Account() {

        }

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            try {
                semaphore.acquire();
                int newBalance = balance + amount;
                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException ignored) {
            } finally {
                semaphore.release();
            }
        }
    }
}


