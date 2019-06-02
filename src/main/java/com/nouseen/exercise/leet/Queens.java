package com.nouseen.exercise.leet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nouseen on 2019/5/29.
 */
public class Queens {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            int[] site = new int[n];
            site[0] = j;
            TreeNode treeNode = new TreeNode(j, n);

            deal(site, treeNode, n, 1);

            List<String> subResult = new LinkedList<>();
            result.add(subResult);
            shiftTreeNode(treeNode, result, subResult, n);
        }
        List<List<String>> finalResult = new LinkedList<>();

        for (List<String> strings : result) {
            if (strings.size() != n) {
                continue;
            }
            finalResult.add(strings);
        }

        return finalResult;
    }

    /**
     * deal
     *
     * @param site
     * @param
     * @return
     */
    public void deal(int[] site, TreeNode treeNode, int total, int nowIndex) {

        // j作为当前准备使用的值
        for (int j = 0; j < total; j++) {
            boolean isOk = isValueOK(site, nowIndex, j);
            // 如果OK则放入site，递归调用
            if (isOk) {
                int[] clone = site.clone();
                clone[nowIndex] = j;

                // 在后面添加节点
                TreeNode treeNode1 = new TreeNode(j, total);
                treeNode.addSubNode(treeNode1);

                // 如果当前已经是最后一个了，则不用递归了
                if (nowIndex + 1 == total) {
                    continue;
                }
                deal(clone, treeNode1, total, nowIndex + 1);
            }
        }
        return;
    }


    public class TreeNode {
        private int value;

        private TreeNode[] subTree;

        private int nowIndex = 0;

        private int subLimit;

        public TreeNode(int value, int subLimit) {
            this.value = value;
            this.subLimit = subLimit;
            this.subTree = new TreeNode[subLimit];
        }

        public void addSubNode(TreeNode treeNode) {
            subTree[nowIndex++] = treeNode;
        }

        public TreeNode getSubNode(int i) {
            return subTree[i];
        }

        public int getNowIndex() {
            return nowIndex;
        }

        public int getValue() {
            return value;
        }
    }


    public String buildQueueString(int length, int queueIndex) {
        char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            result[i] = '.';
        }
        result[queueIndex] = 'Q';

        return String.copyValueOf(result);
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

    // 转换节点
    public void shiftTreeNode(TreeNode treeNode, List<List<String>> result, List<String> oneAnswer, int i) {

        // 先拼上节点，如果有子节点，则分别拼上子节点
        String s = buildQueueString(i, treeNode.getValue());
        oneAnswer.add(s);

        // 再接上第二列
        int nowIndex = treeNode.getNowIndex();
        if (nowIndex == 0) {
            return;
        }
        for (int j = 1; j < nowIndex; j++) {
            // 分支结果
            LinkedList<String> linkedList = new LinkedList();
            linkedList.addAll(oneAnswer);
            result.add(linkedList);
            shiftTreeNode(treeNode.getSubNode(j), result, linkedList, i);
        }

        shiftTreeNode(treeNode.getSubNode(0), result, oneAnswer, i);

    }


}
