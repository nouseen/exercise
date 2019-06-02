package com.nouseen.exercise.leet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nouseen on 2019/5/19.
 */
public class ConcatenationAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (s.length() == 0 || words.length == 0 || words[0].length() == 0) {
            return result;
        }
        int perLen = words[0].length();
        int wordsLen = perLen * words.length;
        if (s.length() < wordsLen) {
            return result;
        }
        int m, i, j, match = 0;
        TreeNode tree = buildWordTree(words);
        for (m = 0; m < perLen; m++) {
            for (i = m; i <= s.length() - wordsLen; i = i + perLen) {
                for (j = i + wordsLen - perLen; j >= i; j = j - perLen) {
                    match = matchSubString(tree, i, s, j, perLen);
                    if (match != 0) {
                        i = j;
                        break;
                    }
                }
                if (match == 0) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    static class TreeNode {
        char c;
        int count;
        int checkIdx;
        int checkCount;
        TreeNode[] child;

        TreeNode() {
            this.child = new TreeNode[26];
        }

        TreeNode(char c) {
            this.c = c;
            this.count = 1;
            this.child = new TreeNode[26];
        }

        boolean check(int idx) {
            if (idx == checkIdx) {
                checkCount++;
            } else {
                checkIdx = idx;
                checkCount = 1;
            }
            if (checkCount > count) {
                return false;
            }
            return true;
        }
    }

    private TreeNode buildWordTree(String[] words) {
        TreeNode root = new TreeNode(), cursor, temp;
        int idx;
        char c;
        for (String word : words) {
            cursor = root;
            for (int i = 0; i < word.length(); i++) {
                c = word.charAt(i);
                idx = c - 'a';
                temp = cursor.child[idx];
                if (temp != null) {
                    temp.count++;
                    cursor = temp;
                } else {
                    temp = new TreeNode(c);
                    cursor.child[idx] = temp;
                    cursor = temp;
                }
            }
        }
        return root;
    }

    private int matchSubString(TreeNode tree, int idx, String s, int start, int len) {
        TreeNode cursor = tree, temp;
        int end = start + len;
        for (int i = start; i < len + start; i++) {
            char c = s.charAt(i);
            temp = cursor.child[c - 'a'];
            if (temp == null) {
                return 1;
            }
            if (!temp.check(idx)) {
                return 2;
            }
            cursor = temp;
        }
        return 0;
    }
}
