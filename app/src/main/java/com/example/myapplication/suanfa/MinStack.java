package com.example.myapplication.suanfa;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by wangzhaosheng on 2020-06-28
 * Description  https://leetcode-cn.com/problems/min-stack/submissions/
 */
class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;//todo 巧妙的辅助栈  可以抽象的归结为两个数据结构共同存储一组数据.比如LinkedHashMap-hashmap加linkedlist
    private int currentMin;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            currentMin = x;
        }
        stack.push(x);
        currentMin = (x < currentMin) ? x : currentMin;
        minStack.push(currentMin);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
        minStack.pop();
        if (stack.isEmpty()) {
            return;//todo 二次判空
        }
        currentMin = minStack.peek();// TODO: 2020-06-28  栈弹出后,记得更改最小值 思维错误:只专注的考虑pop,没有考虑对其他情况的影响.

    }

    public int top() {
        return stack.peek();

    }

    public int getMin() {
        return minStack.peek();
    }
}