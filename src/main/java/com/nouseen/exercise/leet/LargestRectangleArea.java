package com.nouseen.exercise.leet;

import org.junit.Test;

import java.util.Stack;

public class LargestRectangleArea {

        public int largestRectangleArea(int[] heights) {
            Stack< Integer > stack = new Stack < > ();
            stack.push(-1);
            int maxarea = 0;
            for (int i = 0; i < heights.length; ++i) {
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                    maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
                stack.push(i);
            }
            while (stack.peek() != -1)
                maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
            return maxarea;
        }

    @Test
    public void testResult() {
        int[] in = new int[]{2, 1, 5, 6, 2, 3};
        int i = getMaxArea(in);
        System.out.println(i);
    }

    /**
     * 获取最大面积
     * @auth nouseen
     * @since 2020/2/14
     */
    public int getMaxArea(int[] heights) {

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);

        int maxArea = 0;
        // 遍历输入数组
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            // 如果栈中对应最大值小于当前值，则开始出栈，如果是负1，则不用出栈
            while (stack.peek() != -1 && heights[stack.peek()] > height) {

                // 面积为当前被弹出的柱子和上一轮push进的柱子之间的面积
                Integer pop = stack.pop();
                int nowArea = heights[pop] * (i - stack.peek() - 1);
                maxArea = Integer.max(nowArea, maxArea);
            }

            stack.push(i);
        }

        // 剩余的全部出栈
        while (stack.peek() != -1) {
            Integer pop = stack.pop();
            int nowArea = heights[pop] * (heights.length - stack.peek() - 1);
            maxArea = Integer.max(nowArea, maxArea);
        }

        return maxArea;


    }
}
