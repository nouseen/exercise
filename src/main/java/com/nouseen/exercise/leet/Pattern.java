package com.nouseen.exercise.leet;

/**
 * Created by nouseen on 2019/5/19.
 */
public class Pattern {


    public boolean isMatch2(String s, String p) {
        //  通用匹配
        byte[][] resultCache = new byte[s.length() + 1][p.length() + 1];
        return commonMatch1(0, 0, s.toCharArray(), p.toCharArray(), resultCache);

    }

    /**
     * 通用匹配
     *
     * @param sIndex
     * @param pIndex
     * @param s
     * @param p
     * @return
     */
    private boolean commonMatch1(int sIndex, int pIndex, char[] s, char[] p, byte[][] resultCache) {

        if (resultCache[sIndex][pIndex] == 1) {
            return false;
        }

        if (s.length == sIndex && p.length == pIndex) {
            return true;
        }

        // 先判断有没有
        if (!isIndexExist(sIndex, s)) {
            // s 没有了，如果p剩下的全是通配，则返回true
            if (isIndexExist(pIndex + 1, p) && p[pIndex + 1] == '*') {
                if (commonMatch1(sIndex, pIndex + 2, s, p, resultCache)) {
                    return true;
                }
            }
            resultCache[sIndex][pIndex] = 1;
            return false;
        }
        if (!isIndexExist(pIndex, p)) {
            resultCache[sIndex][pIndex] = 1;
            return false;
        }

        // 处理通配符*,如果下一个是星,则开始尝试匹配一次或匹配0次,*号永远被提前处理
        if (isIndexExist(pIndex + 1, p) && p[pIndex + 1] == '*') {
            if (commonMatch1(sIndex, pIndex + 2, s, p, resultCache
            )) {
                return true;
            }

            // 匹配一个,匹配一个的前提是当前值和通配的相同
            if (charEqual(s[sIndex], p[pIndex]) && commonMatch1(sIndex + 1, pIndex, s, p, resultCache)) {
                return true;
            }
        }

        if (charEqual(s[sIndex], p[pIndex]) && commonMatch1(sIndex + 1, pIndex + 1, s, p, resultCache)) {

            return true;
        }
        resultCache[sIndex][pIndex] = 1;
        return false;
    }

    /**
     * 是否结束了，如果是结束了，说明没有下一个字符了
     *
     * @param index
     * @param s
     * @return
     */
    private boolean isIndexExist(int index, char[] s) {
        return index < s.length;
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
}
