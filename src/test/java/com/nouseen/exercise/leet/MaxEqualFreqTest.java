package com.nouseen.exercise.leet;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxEqualFreqTest {

    @Test
    public void testCal() {

        int[] numbers = new int[]{
                2,2,1,1,5,3,3,5
        };

        int[] numberTimes = new int[10];

        for (int number : numbers) {
            numberTimes[number] = numberTimes[number]++;
        }

        // 找到最小的出现次数
        int timesA = 0, timesB = 0;
        for (int numberTime : numberTimes) {
            if (numberTime != 0) {
                if (timesA == 0) {
                    timesA = 0;
                }
            }
        }


    }

}