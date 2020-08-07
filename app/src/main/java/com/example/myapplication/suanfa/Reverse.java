package com.example.myapplication.suanfa;

/**
 * Created by wangzhaosheng on 2020-06-30
 * Description
 * 链表翻转 递归方式  https://leetcode-cn.com/problems/reverse-linked-list/
 * todo 其实跟循环方式一样....可以改成循环  记录两个元素就可以完成翻转
 */
public class Reverse {

    class ListNode {
        int val;
        ListNode next;
    }


    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        head.next = null;
        return resverse(head, next);
    }

    private ListNode resverse(ListNode first, ListNode second) {
        if (second == null) {
            return first;
        }
        ListNode next = second.next;
        second.next = first;
        return resverse(second, next);
    }

}
