package com.nouseen.exercise.leet;

import org.junit.Test;
import sun.security.util.Length;

import java.util.Stack;

public class MaxProfit {


    public int maxProfit(int[] prices) {

        if (prices.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(- 1);

        int maxValue = 0;
        for (int i = 0; i < prices.length; i++) {

            while (stack.peek() != - 1 && prices[i] < prices[stack.peek()]) {

                // 弹出
                Integer pop = stack.pop();

                if (stack.peek() != - 1) {
                    // 计算当前值和栈底值的差
                    maxValue = Integer.max(prices[pop] - prices[stack.get(1)], maxValue);
                }
            }
            stack.push(i);

        }

        int val = prices[prices.length - 1] - prices[stack.get(1)];

        maxValue = Integer.max(val, maxValue);

        return maxValue;
    }
    public int maxProfit1(int[] prices) {

        if (prices.length == 0) {
            return 0;
        }

        int[] stack = new int[prices.length];

        int offset = -1;

        int maxValue = 0;
        for (int i = 0; i < prices.length; i++) {

            while (offset != -1 && prices[i] < prices[stack[offset]]) {

                // 弹出
                Integer pop = stack[offset--];

                if (offset != - 1) {
                    // 计算当前值和栈底值的差
                    maxValue = Integer.max(prices[pop] - prices[stack[0]], maxValue);
                }
            }
            stack[++offset] = i;

        }

        int val = prices[prices.length - 1] - prices[stack[0]];

        maxValue = Integer.max(val, maxValue);

        return maxValue;
    }

    public int maxProfit2(int[] prices) {

        if (prices.length == 0) {
            return 0;
        }

        int minPrice = prices[0];
        int maxValue = 0;

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            minPrice = Integer.min(price, minPrice);
            maxValue = Integer.max(price - minPrice, maxValue);
        }

        return maxValue;
    }

    @Test
    public void testMax() {

        // int[] price = {7, 2, 5, 6, 1, 4};
        // int[] price = {7,6,4,3,1};
        // int[] price = {};
        int[] price = {7, 1, 5, 3, 6, 4};
        int i = maxProfit2(price);
        System.out.println(i);

    }

}
