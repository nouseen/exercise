package com.nouseen.exercise.leet;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nouseen on 2019/5/29.
 */
public class QueensTest {

    Queens queens = new Queens();

    @Test
    public void testString(){
        String s = queens.buildQueueString(5, 5);
        System.out.println(s);
    }

    /**
     * 值是否可用
     *
     * @param site
     * @param nowIndex
     * @param j
     * @return
     */
    private boolean isValueOK(int[] site, int nowIndex, int j) {

        for (int i = 0; i < nowIndex; i++) {
            int x = site[i];
            if (x == j || x + nowIndex - i == j || x - (nowIndex - i) == j) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testDeal(){
        int i = 10;

        List<List<String>> lists = queens.solveNQueens(i);

        int j = 0;
        for (List<String> strings : lists) {

            System.out.println(String.format("当前第%s组答案：", ++j));
            for (String string : strings) {
                System.out.println(string);
            }
        }

    }



    @Test
    public void testNowIndex(){
        int[] arrayResult =new int[5];
        arrayResult[0] = 0;
        arrayResult[1] = 4;

        for (int i = 0; i < arrayResult.length; i++) {
            boolean isOk = isValueOK(arrayResult, 2, i);
            System.out.println(String.format("当前：%s，结果：%s", i, isOk));
        }

    }
    
    @Test
    public void test转换节点(){
        Queens.TreeNode treeNode =  queens.new TreeNode(0, 3);
        Queens.TreeNode treeNode1 = queens.new TreeNode(2, 3);
        Queens.TreeNode treeNode2 = queens.new TreeNode(3, 3);
        Queens.TreeNode treeNode3 = queens.new TreeNode(4, 3);

        treeNode.addSubNode(treeNode1);
        treeNode.addSubNode(treeNode2);
        treeNode1.addSubNode(treeNode3);

        List<List<String>> result = new LinkedList<>();
        List<String> subResult = new LinkedList<>();
        result.add(subResult);

        queens.shiftTreeNode(treeNode, result, subResult, 5);

        int i = 0;
        for (List<String> strings : result) {
            System.out.println(String.format("当前第%s组答案：", ++i));
            for (String string : strings) {
                System.out.println(string);
            }
        }

    }

}