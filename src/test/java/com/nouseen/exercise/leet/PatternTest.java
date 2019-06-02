package com.nouseen.exercise.leet;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by nouseen on 2019/5/14.
 */
public class PatternTest {

    /**
     * 通用匹配
     *
     * @param sIndex
     * @param pIndex
     * @param s
     * @param p
     * @return
     */
    private boolean commonMatch(int sIndex, int pIndex, String s, String p) {

        if (s.length() == 0) {
            if (p.length() == 0) {
                return true;
            }

            if (p.length() % 2 != 0) {
                return false;
            }

            for (int i = 1; i < p.length(); i += 2) {
                // 如果i是奇数位,且不是*,则返回false
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        // 先判断有没有
        if (sIndex >= s.length()) {
            return false;
        }
        if (pIndex >= p.length()) {
            return false;
        }

        // 开始处理，从0号位开始处理，一旦处理到最后一位，则需要判断是否可以结束，所以需要一个是否可以结束的方法
        for (; ; ) {

            // 输入
            char schar = s.charAt(sIndex);
            // 正则
            char pchar = p.charAt(pIndex);

            // 如果等
            if (charEqual(schar, pchar)) {
                int checkResult = checkIndex(pIndex, sIndex, p, s);
                if (checkResult == 1) {
                    return true;
                }
                if (checkResult == -1) {
                    return false;
                }

                pIndex++;
                sIndex++;

            } else {

                // 如果正则是*，则消耗完所有的同样的输入
                if (pchar == '*') {
                    char needDeal = p.charAt(pIndex - 1);

                    // 计算出相等的数量
                    while (charEqual(s.charAt(sIndex), needDeal)) {

                    }

                    // 如果是.*，则要从匹配0个到n个都计算一次，直到成功为止
                    if (needDeal == '.') {
                        // 如果是最后一个，则返回true
                        if (isTheLast(pIndex, p)) {
                            return true;
                        }

                        for (int k = 0; k <= s.length() - sIndex; k++) {
                            if (commonMatch(sIndex - 1, pIndex + 1, s, p)) {
                                return true;
                            }
                            sIndex++;
                        }
                        return false;
                    }

                    // 校验
                    while (charEqual(s.charAt(sIndex), needDeal)) {
                        int checkResult = checkIndex(pIndex, sIndex, p, s);

                        if (checkResult == 1) {
                            return true;
                        }
                        if (checkResult == -1) {
                            return false;
                        }
                        sIndex++;
                    }

                    if (!isIndexExist(++pIndex, p)) {
                        return false;
                    }
                }

                // 如果是要匹配0个的话，则直接判断下一个字符是不是*，如果是的话，再下一个和当前字符是否一样，如果是一样的话，则放行
                else if (isIndexExist(++pIndex, p) && p.charAt(pIndex) == '*') {

                    int checkResult = checkIndex(pIndex, sIndex - 1, p, s);

                    if (checkResult == 1) {
                        return true;
                    }
                    if (checkResult == -1) {
                        return false;
                    }
                    pIndex++;
                } else {
                    return false;
                }
            }

        }
    }




    /**
     * 替换掉无用数据
     *
     * @param p
     * @return
     */
    private String replaceUnUseValue(String p) {

        StringBuilder pBuilder = new StringBuilder();

        // 替换掉 a*a结构为a*
        for (int i = 0; i < p.length(); i++) {

            int repeatNumber = 0;
            char repeatChar = 0;
            if (i - 1 >= 0) {
                repeatChar = p.charAt(i - 1);
            }
            while (p.charAt(i) == '*' && repeatChar != '.' && i != 0 && i + repeatNumber + 1 < p.length() && p.charAt(i + repeatNumber + 1) == repeatChar) {
                repeatNumber++;
            }
            i += repeatNumber;
            if (repeatNumber > 0) {
                pBuilder.append('*');
                i++;
            }

            if (i < p.length()) {
                pBuilder.append(p.charAt(i));
            }
        }

        p = pBuilder.toString();
        return p;
    }

    /**
     * 每次Index增加前要判断是否结束
     *
     * @param sIndex
     * @param pIndex
     * @param p
     * @param s
     * @return 0:需要继续处理  1：不需要处理，己成功 -1：不需要处理，己失败
     */
    private int checkIndex(int pIndex, int sIndex, String p, String s) {

        // 如果p为空，s不为空，返回-1
        if (p.length() == 0 && s.length() != 0) {
            return -1;
        }

        if (s.length() == 0) {
            if (p.length() == 0) {
                return 1;
            } else if (p.length() == 2 && p.charAt(1) == '*') {
                return 1;
            } else {
                return -1;
            }
        }

        // 如是都不是最后一个，则需要继续处理
        if (!isTheLast(sIndex, s) && !isTheLast(pIndex, p)) {
            return 0;
        }

        // 如果都是最后一个，则不需要继续处理，且匹配成功
        if (isTheLast(sIndex, s) && isTheLast(pIndex, p)) {
            return 1;
        }

        //  如果parttern是最后一个，但是输入还不是
        if (isTheLast(pIndex, p) && p.charAt(pIndex) == '*') {
            char pVal = p.charAt(pIndex - 1);
            do {
                char sVal = s.charAt(++sIndex);
                // 如果输入不能匹配*前面的值，则匹配失败
                if (sVal != pVal) {
                    return -1;
                }
            }
            // 输入是否不是最后一个
            while (!isTheLast(sIndex, s));

            // 输入消耗完结毕，则返回true
            return 1;
        }

        // 输入己经完了，但是partten还有，则判断最后一个是不是通配，如果不是通配，则失败，否则成功
        if (isIndexExist(++pIndex, p) && isTheLast(pIndex, p) && p.charAt(pIndex) == '*') {
            return 1;
        }

        // 最后两个是字符加通配也可以给过
        if (isIndexExist(++pIndex, p) && isTheLast(pIndex, p) && p.charAt(pIndex) == '*') {
            return 1;
        }

        return -1;

    }

    /**
     * 是否结束了，如果是结束了，说明没有下一个字符了
     *
     * @param index
     * @param s
     * @return
     */
    private boolean isTheLast(int index, String s) {
        return index >= s.length() - 1;
    }



    @Test
    public void testIsStringEnd() {
        String a = "didiofoif";
        boolean stringEnd = isTheLast(8, a);
        System.out.println(stringEnd);
    }

    @Test
    public void testIsMatch() {
        Pattern pattern = new Pattern();
        String s = "ab", p = "a*b";
        boolean match =  pattern.isMatch2(s, p);
        Assert.assertTrue(match);

        s = "abcd";
        p = "a*b";
        Assert.assertFalse(s + "   " + p,  pattern.isMatch2(s, p));
        s = "aaaaab";
        p = "a*b";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "a";
        p = "a*";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "ab";
        p = "a*b";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "b";
        p = "a*b";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "abb";
        p = "a*b*";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "aaaaabbbbbbb";
        p = "a*b*";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "";
        p = "a*";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "ddd";
        p = "a*ddd";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "ddd";
        p = "a*b*d*dd";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "bbab";
        p = "b*a*";
        Assert.assertFalse(s + "   " + p,  pattern.isMatch2(s, p));
        s = "abc";
        p = ".*";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "abd";
        p = ".*.*";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "";
        p = "a*b";
        Assert.assertFalse(s + "   " + p,  pattern.isMatch2(s, p));
        s = "ab";
        p = ".*c";
        Assert.assertFalse(s + "   " + p,  pattern.isMatch2(s, p));

        s = "a";
        p = "ab*";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "a";
        p = "b*a";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "aa";
        p = "ab*a";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "aaa";
        p = "ab*ac*a";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "";
        p = "b*a";
        Assert.assertFalse(s + "   " + p,  pattern.isMatch2(s, p));
        s = "aa";
        p = "b*a*c*a";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "aaa";
        p = "ab*a*c*a";
        Assert.assertTrue(s + "   " + p,  pattern.isMatch2(s, p));
        s = "a";
        p = "ab*a";
        Assert.assertFalse(s + "   " + p,  pattern.isMatch2(s, p));

    }

    @Test
    public void testCheckIndex() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ab", "a*b");
        hashMap.put("ac", "ac");
        hashMap.put("aaaaab", "aa*b");
        hashMap.put("aaaaabbbbbbb", "a*b*");
        hashMap.put("b", "ba*");
        hashMap.put("bbab", "b*a*");

        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            int i = checkIndex(1, 1, entry.getValue(), entry.getKey());
            System.out.println(String.format("%s  %s  %s", entry.getKey(), entry.getValue(), i));
        }
    }

    @Test
    public void testCheckIndex1() {
        int i = checkIndex(1, 0, "a*b", "ab");
        System.out.println(i);

        i = checkIndex(1, 0, "a*ba", "a");
        System.out.println(i);

        i = checkIndex(3, 3, "a*b*", "abbbbbbb");
        System.out.println(i);
    }

    @Test
    public void testReplaceUnUseValue() {
        String a = "a*b*d*ddyy";
        String s = replaceUnUseValue(a);
        System.out.println(s);
    }

    /**
     * 是否结束了，如果是结束了，说明没有下一个字符了
     *
     * @param index
     * @param s
     * @return
     */
    private boolean isIndexExist(int index, String s) {
        return index < s.length();
    }
    /**
     * char相等
     *
     * @param a
     * @param b
     * @return
     */
    private boolean charEqual(char a, char b) {
        return a == '.' || b == '.' || a == b;
    }

    @Test
    public void testCharEqual() {
        boolean a = charEqual('.', 'a');
        System.out.println(a);
    }


    public boolean isMatch(String s, String p) {
        if (s==null||p==null) return false;
        int[][] buf = new int[s.length()][p.length()];
        return isMatch(s.toCharArray(), p.toCharArray(), s.length()-1, p.length()-1, buf);

    }

    public boolean isMatch(char[] S, char[] P, int i, int j, int[][] buf) {

        if (j==-1) {
            return i==-1;
        }

        if (i>=0 && j>=0 && buf[i][j]!=0) {
            return buf[i][j]==1;
        }
        boolean ret;
        if (P[j]=='.')
            ret = isMatch(S,P,i-1,j-1, buf);
        else if (P[j]=='*')
            ret = (i>=0&&j>=1&&charMatch(S[i],P[j-1])&&isMatch(S,P,i-1,j, buf)) || isMatch(S,P,i,j-1, buf) || isMatch(S,P,i,j-2, buf) ;
        else
            ret = i>=0 && j>=0 && S[i]==P[j] && isMatch(S,P,i-1,j-1, buf);

        if (i>=0 && j>=0) {
            buf[i][j]=ret?1:2;
        }

        return ret;

    }

    public boolean charMatch(char c1, char c2) {
        if (c1==c2) return true;
        else if (c2=='.') return true;
        else return false;
    }

}
