package com.nouseen.exercise.leet;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

import static org.junit.Assert.*;

public class ZeroEvenOddTest {

    @Test
    public void test() throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    zeroEvenOdd.zero(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println("zero -" + value);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    zeroEvenOdd.even(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println("even -" + value);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    zeroEvenOdd.odd(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println("odd -" + value);

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(100);
    }

    private int n = 10;

    private volatile int i = 0;


    volatile Thread zeroThread;
    volatile Thread evenThread;
    volatile Thread oddThread;

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        zeroThread = Thread.currentThread();

        while (true) {

            if (evenThread == null || oddThread == null) {
                continue;
            }

            if (i >= n) {
                i = - 1;
                LockSupport.unpark(evenThread);
                LockSupport.unpark(oddThread);
                return;
            }

            printNumber.accept(0);

            if (++ i % 2 == 1) {
                LockSupport.unpark(oddThread);
            } else {
                LockSupport.unpark(evenThread);
            }
            LockSupport.park();

        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        evenThread = Thread.currentThread();
        while (true) {

            LockSupport.park();

            if (i == - 1) {
                return;
            }

            printNumber.accept(i);
            LockSupport.unpark(zeroThread);
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        oddThread = Thread.currentThread();
        while (true) {
            LockSupport.park();

            if (i == - 1) {
                return;
            }

            printNumber.accept(i);
            LockSupport.unpark(zeroThread);
        }
    }
}