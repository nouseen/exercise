package com.nouseen.exercise.leet;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class CoinChangeTest {

    public int coinChange1(int[] coins, int amount) {

        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? - 1 : dp[amount];
    }

    int minCount = Integer.MAX_VALUE;


    public int coinChange5(int[] coins, int amount) {
        Arrays.sort(coins);
        recursion(coins, amount, 0, coins.length - 1);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    void recursion(int[] coins, int amount, int count, int index) {
        if (index < 0 || count + amount / coins[index] >= minCount) return;
        if (amount % coins[index] == 0) {
            minCount = Math.min(minCount, count + amount / coins[index]);
            return;
        }
        for (int i = amount / coins[index]; i >= 0; i--) {
            recursion(coins, amount - i * coins[index], count + i, index - 1);
        }
    }

    public int coinChange3(int[] coins, int amount) {

        //存上计算结果
        int[] changeResult = new int[amount + 1];
        Arrays.fill(changeResult, amount + 1);
        changeResult[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    changeResult[i] = Integer.min(changeResult[i], changeResult[i - coins[j]] + 1);
                }
            }
        }

        int i = changeResult[amount];
        if (i > amount) {
            return - 1;
        }

        return i;

    }

    @Test
    public void test() {

        int[] coins = {186,419,83,408};

        int amount = 6249;

        int i = coinChange3(coins, amount);

        System.out.println(i);
    }
    @Test
    public void test5() {

        int[] coins = {10,8,1};

        int amount = 104;

        int i = coinChange5(coins, amount);

        System.out.println(i);
    }

    @Test
    public void testQuickSort() {

        int[] coins = {1, 2, 5, 8, 7};

        quickSort(coins);

        for (int coin : coins) {
            System.out.println(coin);
        }

    }

    public int coinChange(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return - 1;
        }

        quickSort(coins);

        // 先排序后计算
        int result = getResult(coins, amount, 0, coins.length - 1, new int[amount]);

        return result;
    }


    public int getResult(int[] coins, int amount, int number, int coinIndex,int[] changeCache) {

        int cacheValue = changeCache[amount - 1];
        if (cacheValue != 0) {
            return cacheValue;
        }

        int miniValue = Integer.MAX_VALUE;

        boolean flag = false;
        // 从大到小尝试
        for (int i = coinIndex; i >= 0; i--) {

            int val = coins[i];

            if (val > amount) {
                continue;
            }

            if (val == amount) {
                miniValue = Integer.min(number + 1, miniValue);
                flag = true;
                continue;
            }

            int result = getResult(coins, amount - val, number + 1, i, changeCache);
            if (result != - 1) {
                flag = true;
                miniValue = Integer.min(result, miniValue);
                continue;
            }
        }
        if (! flag) {
            changeCache[amount - 1] = -1;
            return - 1;
        }

        changeCache[amount - 1] = miniValue;
        return miniValue;
    }
    /**
     * 快速排序
     * @param array
     */
    public static void quickSort(int[] array) {
        int len;
        if(array == null
                || (len = array.length) == 0
                || len == 1) {
            return ;
        }
        sort(array, 0, len - 1);
    }

    /**
     * 快排核心算法，递归实现
     * @param array
     * @param left
     * @param right
     */
    public static void sort(int[] array, int left, int right) {
        if(left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }
}