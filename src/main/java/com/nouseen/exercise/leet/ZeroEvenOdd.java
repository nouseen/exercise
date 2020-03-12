package com.nouseen.exercise.leet;

import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

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