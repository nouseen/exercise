package com.nouseen.exercise.leet;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {

    public int binSearch(int val) {
        int head = 1;
        int tail = val;
        int result = 0;

        if (tail >= (1 << 16)) {
            tail = (1<<16) -1;
        }

        result = (head + tail) / 2;

        while (true) {
            if ((result - 1) * (result - 1) >= val) {
                tail = result - 1;
                result = (head + tail) / 2;
            } else if (result * result < val) {
                head = result + 1;
                result = (head + tail) / 2;
            } else {
                return result;
            }
        }
    }

    @Test
    public void test() {
        int i = binSearch(15754);
        System.out.println(i);
    }

    @Test
    public void test1() {
        System.out.println(31*31);
        System.out.println(32*32);
    }
}