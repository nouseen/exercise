package com.nouseen.exercise.leet;

/**
 * Created by nouseen on 2018/3/31.
 */
public class MaxLength {
    public  String theString = "";
    public  int theStringLenth;
    public  int[] charNumber = new int[Character.MAX_VALUE];
    public int startOfI;

    // 是否需要清数占位数据
    public boolean isCharNumberNeedClear = true;
    
    public void setTheString(String val){
        this.theString = val;
        theStringLenth = val.length();
    }
    
    public int lengthOfLongestSubstring(String s) {
        theString = s;
        theStringLenth = s.length();
        int result = s.length() == 0 ? 0 : 1;

        int maxResult = calDiffCharNumber(s);

        // 开始位置
        for (int i = 0; i < theStringLenth; i++) {
            if (i < startOfI) {
                i = startOfI;
            }
            if (theStringLenth - i - 1< result) {
                return result;
            }

            // 第一次开始时需清除数据
            isCharNumberNeedClear = true;

            // 结束位置，结束位置增加时不需要清除站位表
            for (int j = i + result; j < theStringLenth; j++) {
                
                boolean stringNotDuplicate = isStringNotDuplicate(i, j);

                // 如果重复了，则不继续执行
                if (! stringNotDuplicate) {
                    int nowVaule = j - i;
                    if (nowVaule > result) {
                        result = nowVaule;
                        if (result == maxResult) {
                            return maxResult;
                        }
                    }
                    break;
                } else {

                    // 如果不重复，则判断是否为最后一个，如果是最后一个，则直接返回，否则继续加下一个进行判断
                    if (j != theStringLenth - 1) {
                        // 如果不是最后一个，则下一次判断是不清占位数据，直接加新判断
                        isCharNumberNeedClear = false;
                    } else {
                        // 如果是最后一个，则更新结果数据
                        int nowVaule = j - i + 1;
                        if (nowVaule > result) {
                            result = nowVaule;
                            if (result != maxResult) {
                            } else {
                                return maxResult;
                            }
                        }
                    }


                }


            }
        }
        return result;
    }

    /**
     * 子串在指定区间是否重复
     *
     * @return
     */
    public final boolean isStringNotDuplicate(int start, int end) {
        
        clearCharList();

        for (int i = start; i <= end; i++) {
            // 如果不清占全数据,则直接从end开始
            if (! isCharNumberNeedClear) {
                i = end;
            }
            
            char c1 = theString.charAt(i);
            // 如果重复了，则清除占位数据
            if (++charNumber[(int)c1] >= 2) {
                startOfI = theString.indexOf(c1, start);
                
                // 下一次执行时清除占位数据
                isCharNumberNeedClear = true;
                return false;
            }
        }

        // 下一次执行时不清
        isCharNumberNeedClear = false;
        return true;
    }


    /**
     * 清除占位数据
     */
    public final void clearCharList() {
        if (isCharNumberNeedClear) {
            for (int i = 0; i < charNumber.length; i++) {
                charNumber[i] = 0;
            }
        }
    }

    /**
     * 计算可能出现的最长子串
     * @return
     */
    public final int calDiffCharNumber(String val) {
        
        for (int i = 0; i < val.length(); i++) {
            char c = val.charAt(i);
            charNumber[(int) c] = 1;
        }

        int sum = 0;
        for (int i = 0; i < charNumber.length; i++) {
            sum += charNumber[i];
            charNumber[i] = 0;
        }
        return sum;
    }
}
