package com.company;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.*;

public class Main {

    private static Account account=new Account();
    public static void main(String[] args) {
        // write your code here
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();

        System.out.println("Thread 1\t\tThread 2\t\tBalance");
    }

    private static class DepositTask implements Runnable {
        public void run(){
            try{
                while(true) {
                    account.deposit((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public static class WithdrawTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                account.withdraw((int) (Math.random() * 10) + 1);
            }
        }
    }
    private static class Account {
        private static Lock lock = new ReentrantLock();
        private static Condition newDeposit = lock.newCondition();
        private int balance = 0;

//        Account() {
//
//        }

        public int getBalance() {
            return balance;
        }
        public void withdraw(int amount) {
//            lock.lock();
            synchronized (this) {
                try {
                    while (balance < amount) {
                        System.out.print("\t\tWait for a deposit");
//                    newDeposit.await();
                        this.wait();
                    }
                    balance -= amount;
                    System.out.print("\t\tWithdraw " + amount +
                            "\t\t" + getBalance());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                lock.unlock();
                }
            }
        }

        public void deposit(int amount) {
            synchronized (this) {
//                lock.lock();
                try {
                    balance += amount;
                    System.out.print("Deposit " + amount +
                            "\t\t\t\t\t" + getBalance());
//                newDeposit.signalAll();
                    this.notifyAll();
                } finally {
//                    lock.unlock();
                }
            }
        }
    }

}



