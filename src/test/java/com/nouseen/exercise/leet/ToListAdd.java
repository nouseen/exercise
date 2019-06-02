package com.nouseen.exercise.leet;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by nouseen on 2019/5/14.
 */
public class ToListAdd {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode now1 = l1;
        ListNode now2 = l2;
        ListNode answer = new ListNode(0);

        ListNode voidNode = new ListNode(0);

        ListNode nowAnswerNode = answer;

        while (true) {
            int val1 = now1.val;
            int val2 = now2.val;

            // 生成下一个结果节点
            ListNode nextAnswerNode = new ListNode(0);
            nowAnswerNode.next = nextAnswerNode;

            if (nowAnswerNode.val + val1 < 10) {
                nowAnswerNode.val = nowAnswerNode.val + val1;
            }else {
                nowAnswerNode.val = nowAnswerNode.val + val1 - 10;
                nextAnswerNode.val = nextAnswerNode.val + 1;
            }

            if (nowAnswerNode.val + val2 < 10) {
                nowAnswerNode.val = nowAnswerNode.val + val2;
            }else {
                nowAnswerNode.val = nowAnswerNode.val + val2 - 10;
                nextAnswerNode.val = nextAnswerNode.val + 1;
            }

            // 当前节点计算完毕，计算下一个节点
            ListNode next1 = now1.next;
            ListNode next2 = now2.next;
            if (next1 != null && next2 != null) {
                now1 = next1;
                now2 = next2;
            } else if(next1 == null && next2 == null){
                if (nextAnswerNode.val == 0) {
                    nowAnswerNode.next = null;
                }
                break;
            }else if (next1 == null) {
                now1 = voidNode;
                now2 = next2;
            } else if (next2 == null) {
                now1 = next1;
                now2 = voidNode;
            }
            nowAnswerNode = nextAnswerNode;
        }

        return answer;
    }

    ListNode listNode0 = new ListNode(9);
    ListNode listNode3 = new ListNode(9);

    @Before
    public void testInit(){
        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(9);

        listNode0.next = listNode1;
        listNode1.next = listNode2;

        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
    }

    @Test
    public void testLength(){
        int lenght2 = 1;
        ListNode now = listNode0.next;

        while (true) {
            lenght2++;
            now = now.next;
            if (now == null) {
                break;
            }
        }

        System.out.println(lenght2);
    }
    
    @Test
    public void testResult(){

        ListNode now1 = listNode0;
        ListNode now2 = listNode3;

        ListNode listNode = addTwoNumbers(now1, now2);

        ListNode iter = listNode;
        while (iter != null) {
            System.out.println(iter.val);
            iter = iter.next;
        }

    }
}
