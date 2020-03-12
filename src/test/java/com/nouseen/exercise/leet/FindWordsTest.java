package com.nouseen.exercise.leet;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindWordsTest {

    @Test
    public void testByte() {
        byte[] bytes = {0, 1};
        byte[] clone = bytes.clone();
        clone[0] = 1;
        System.out.println(bytes[0]);

    }
    @Test
    public void testFind() {
        String[] strings = {"oath", "pea", "eat", "rain"};
        char[][] chars = {{'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};
        List<String> words = findWords(chars, strings);
        for (String word : words) {
            System.out.println(word);
        }
    }

    @Test
    public void testFind1() {
        String[] strings = {"cdba"};
        char[][] chars = {{'a', 'b'},
                {'c', 'd'}};
        List<String> words = findWords(chars, strings);
        for (String word : words) {
            System.out.println(word);
        }
    }
    @Test
    public void testSearch() {
        Tries tries = new Tries();
        boolean ety = tries.search("ety");
        System.out.println(ety);
    }

    @Test
    public void testSearch1() {
        Tries tries = new Tries();
        tries.insert("et");
        tries.insert("etuu");
        tries.insert("btuu");
        tries.insert("ety");
        System.out.println(tries.search("etu"));
        System.out.println(tries.search("ety"));
        System.out.println(tries.search("et"));
        System.out.println(tries.search("e"));
        System.out.println(tries.search("bty"));
    }


    public List<String> findWords(char[][] board, String[] words) {

        Tries tries = new Tries();

        for (String word : words) {
            tries.insert(word);
        }


        Set<String> resultCollection = new HashSet<>();


        // 对列表进行搜索
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                findWordByIndex(tries.root, board, resultCollection, i, j);

            }
        }
        return new LinkedList<>(resultCollection);
    }

    public void findWordByIndex(WordTreeNode wordTreeNode, char[][] board, Set<String> resultCollection, int i, int j) {

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }

        // 遍历过了
        char nowChar = board[i][j];
        if (nowChar == '1') {
            return;
        }

        char c = board[i][j];

        WordTreeNode node = wordTreeNode.getNode(c);
        if (node == null) {
            return;
        }
        if (node.getVal() != null) {
            resultCollection.add(node.getVal());
        }
        board[i][j] = '1';
        findWordByIndex(node, board, resultCollection, i + 1, j);
        findWordByIndex(node, board, resultCollection, i - 1, j);
        findWordByIndex(node, board, resultCollection, i, j + 1);
        findWordByIndex(node, board, resultCollection, i, j - 1);

        board[i][j] = nowChar;

        return;

    }

    private class Tries {

        private WordTreeNode root = new WordTreeNode();

        public Tries() {

        }

        public void insert(String val) {

            WordTreeNode nowNode = root;

            for (int i = 0; i < val.length(); i++) {
                char s = val.charAt(i);
                WordTreeNode nextNode = nowNode.getNode(s);
                // 不包含则新增节点
                if (nextNode == null) {
                    WordTreeNode newNode = new WordTreeNode();
                    nowNode.addNode(s, newNode);

                    nextNode = newNode;
                }

                // 包含则更新节点
                nowNode = nextNode;

                if (i == val.length() - 1) {
                    nextNode.setVal(val);
                }
            }
        }

        public boolean search(String val) {

            WordTreeNode nowNode = root;

            for (int i = 0; i < val.length(); i++) {
                WordTreeNode node = nowNode.getNode(val.charAt(i));
                if (node == null) {
                    return false;
                }
                nowNode = node;
            }

            if (nowNode.getVal() != null) {
                return true;
            }
            return false;
        }

    }

    private class WordTreeNode {

        private WordTreeNode[] links = new WordTreeNode[26];

        private String val;

        public WordTreeNode[] getLinks() {
            return links;
        }

        public void setLinks(WordTreeNode[] links) {
            this.links = links;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public WordTreeNode getNode(char s) {
            return links[s - 'a'];
        }

        public void addNode(char s, WordTreeNode wordTreeNode) {
            links[s - 'a'] = wordTreeNode;
        }

        public boolean containKey(char c) {

            return getNode(c) != null;
        }

    }

}