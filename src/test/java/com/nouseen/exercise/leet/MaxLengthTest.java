package com.nouseen.exercise.leet;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.Date;

/**
 * Created by nouseen on 2018/4/5.
 */
public class MaxLengthTest {
    MaxLength maxLength = new MaxLength();
    @Test
    public void testArray(){
        int[] charNuber = new int[Character.MAX_VALUE];

        for (int i : charNuber) {
            System.out.println(i);
        }
    }

    @Test
    public void testIsStringDuplicate() {
        String[] testVals = new String[]{
                "abcd",
                "abcda",
                "abcdefg",
                "abcddefg",
                "aab",
        };

        for (String testVal : testVals) {
            maxLength.setTheString(testVal);
            boolean result = maxLength.isStringNotDuplicate(1,2);
            System.out.println(String.format("当前校验字符串：%s，校验结果：%s", testVal, result));
        }

    }

    public int lengthOfLongestSubstring1(String s) {

        // 定义一个数组，坐标对应所有字符，对应的值则为其最后出现的位置，一个字符两个字节，一个字节4位，8位共256
        int[] position = new int[256];

        // 上一次重复的起点位置
        int preRipeatPostion = -1;

        int result = 0;
        // 遍历目标
        int index = 0;
        while (index < s.length()) {
            // 当前字符
            char nowChar = s.charAt(index);

            // 当前字符上次出现的位置
            int prePostion = position[nowChar];

            // 如果当前位置与上次出现的位置的差大于最大位置差，则记录。但是中间有可能有重复的，所以要有一个不重复的当前位移，位移从上一次重复的起点开始
            if(prePostion > preRipeatPostion){
                // 更新重复起点
                preRipeatPostion = prePostion;
            }

            // 更新最大值，不管重复起点有没有被更新，都需要更新最大值，如果有被更新，则是从上次该字母出现位置到这里为当前不重复长度，否则，则是从上次重复点到这里的长度
            result = Math.max(index - preRipeatPostion, result);

            // 更新位置
            position[nowChar] = index++;
        }
        return 0 ;

    }


    public int lengthOfLongestSubstring(String s) {

        // 记录每个字母出现的座标
        int[] list = new int[256];

        // 上一个字母的位置
        int previous = -1,
                // 当前位移
                right = 0,
                // 最大长度
                max_len = 0;
        // 所有字母的位置标为-1
        for(int i=0;i<list.length;i++){
            list[i]=-1;
        }

        // 遍历当前字符串
        while (right < s.length()) {

            //当前字符位
            char c = s.charAt(right);

            // 如果当前字符位大于上一个字母的字符位置，则更新上一个字母的字符位
            if (list[(int) c] > previous) {
                previous = list[(int) c];
            }
            max_len = Math.max(max_len, right - previous);

            list[(int) c] = right++;
        }
        return max_len;
    }

    @Test
    public void testLengthOfLongestSubstring(){
        String[] testVals = new String[]{
                "au",
                "aab",
                "abcd",
                "abcda",
                "abcdefg",
                "abcddefg",
                "abbcdefdg",
                "abcabcbb",
                "bbbbb",
                "dvdf",
                "bhhoejpnsoqioadvynqrbo",
                "jjsangmxbomryahpekexmyzrzjsu",
                "fstmtifkfvyyyrnlfnhedjkivvoxxoachwqcewmj",
                "kggqtmpfxewkqccehsluxmrbgcpmnwwzcdsxrhcolbbdddhmv",
                "kdgqtpndsqkqsqgqoynsnduwsxbwznvlsbensttmkdceukuiijaxowugtxfukageeksydllpontiansizuinrc",
                ""

        };
        for (String testVal : testVals) {

            int i = lengthOfLongestSubstring(testVal);

            System.out.println(String.format("当前计算字符串：%s，计算结果：%s", testVal, i));

        }
    }

    @Test
    public void test(){

        // int i = maxLength.lengthOfLongestSubstring("abcabcbb");
        int i = lengthOfLongestSubstring("cabcbba");
        System.out.println(i);
    }

    class Entrust {

        private String entrustNumber;

        private int type;

        public String getEntrustNumber() {
            return entrustNumber;
        }

        public void setEntrustNumber(String entrustNumber) {
            this.entrustNumber = entrustNumber;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
    @Test
    public void testReturn(){
        Entrust entrust = new Entrust();
        entrust.setType(1);

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        for (int i = 0; i < 100 * 10000; i++) {
            if (entrust.getType() != 1) {
                new Date();
            }

        }
        stopwatch.stop();
        System.out.println(stopwatch.getTime());

    }


}