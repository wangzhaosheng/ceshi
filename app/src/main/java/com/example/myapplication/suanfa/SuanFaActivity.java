package com.example.myapplication.suanfa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SuanFaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suan_fa);
    }

    public void click230(View view) {
        boolean valid = isValid2("(({}}))");
        Toast.makeText(this, valid + "", 1).show();
    }


    //数组中找两个数之和  https://leetcode-cn.com/problems/two-sum/solution/jie-suan-fa-1-liang-shu-zhi-he-by-guanpengchn/
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    int[] b = {i, j};// TODO: 2020-06-17  不能直接返回大括号数组
                    return b;
                }
            }
        }
        return null;
    }


    //数组中找两个数之和  https://leetcode-cn.com/problems/two-sum/solution/jie-suan-fa-1-liang-shu-zhi-he-by-guanpengchn/
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {//todo 不是先放到map里,而是边放边跟放过的比,思路惊奇.复杂度O(n)
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    //解法1: 复杂度  n的平方. 只包含 ( ) [] {} 的字符串.看是否闭合的有效  https://leetcode-cn.com/problems/valid-parentheses/

    public boolean isValid(String s) {
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }
        return s.equals("");
    }


    //解法2: 复杂度 最快是n. 只包含 ( ) [] {} 的字符串.看是否闭合的有效  https://leetcode-cn.com/problems/valid-parentheses/
    //todo 利用栈的回溯  边放边消除成对的括号
    public boolean isValid2(String s) {
        HashMap<Character, Character> characterHashMap = new HashMap<>();
        characterHashMap.put(')', '(');
        characterHashMap.put(']', '[');
        characterHashMap.put('}', '{');
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (aChar == '(' || aChar == '[' || aChar == '{') {
                stack.push(aChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character top = stack.pop();
                    if (top != characterHashMap.get(aChar)) {
                        return false;
                    }

                }
            }
        }
        return stack.isEmpty();

    }

    //todo  算法题目中如果没有特殊说明不考虑判空问题,一般还是要判空


    //自己的解法: 复杂度较高.求一个数组中子数组和的最大值  todo https://leetcode-cn.com/problems/maximum-subarray/submissions/
    public int maxSubArray(int[] nums) {
        int addMax = nums[0];//todo 套路  ,一般设置第一个元素为初始值

        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int addResult = 0; //todo for 循环中的值必须初始化,算是语法问题.
            for (int j = i; j < length; j++) { //todo 一般都是小于length 不是length-1
                addResult += nums[j];
                if (addResult > addMax) {
                    addMax = addResult;
                }
                if (addResult < 0)
                    break;
            }
        }

        return addMax;
    }


    //最优解法: 求一个数组中子数组和的最大值  todo https://leetcode-cn.com/problems/maximum-subarray/submissions/
    public int maxSubArray2(int[] nums) {
        int result = nums[0];
        int sum = 0;

        for (int num : nums) {
//            sum += num;
            if (sum <= 0) { //todo 这里确实很顺,但是为什么想不到,只想到了每次加当前的元素. 思维错误:不知道拿当前元素作为初始化情况
                sum = num;
            } else {
                sum += num;
            }

            result = Math.max(result, sum);

        }
        return result;
    }

    //todo 爬楼梯问题  https://leetcode-cn.com/problems/climbing-stairs/  重做一遍


    //todo 判断环形链表  https://leetcode-cn.com/problems/linked-list-cycle/ 思路是判断节点的地址是否出现过,思路惊奇
    class ListNode {
        int val;
        ListNode next;
    }

    public boolean hasCycle(ListNode head) {
        HashMap map = new HashMap();
        while (head != null) {
            if (map.containsKey(head.hashCode())) { //todo HashMap没有contains方法,只有containsKey方法
                return true;
            }
            map.put(head.hashCode(), 1);
            head = head.next;
        }
        return false;
    }

    //链表相交 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (headA == null || headB == null) {
            return null;
        }
        while (headA != null) {
            map.put(headA.hashCode(), 1);
            headA = headA.next;
        }
        while (headB != null) {
            if (map.containsKey(headB.hashCode())) {

                return headB;
            }
            headB = headB.next;
        }
        return null;

    }

    //链表相交优化 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    //只看最后一个节点
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
//        HashMap<Integer,Integer>map =new HashMap<>();
        if (headA == null || headB == null) {
            return null;
        }
        while (headA.next != null) {//todo 找链表的最后一个元素的话.看next是否为null  不是当前是否为空
            headA = headA.next;
        }
        while (headB != null) {
            if (headB == headA) {
                return headB;
            }
            headB = headB.next;

        }
        return null;

    }

    //求数组最大和,不能取相邻的数据  https://leetcode-cn.com/problems/house-robber/
    public int rob(int[] nums) {
        int len = nums.length;
        if(len==1){
            return nums[0];
        }
        int index = 0;
        int sum = 0
        while (index < len) {
            int current = nums[index];
            if(index == len-1){
                sum+=current;
                break;
            }
            //没有越界 倒数第二位就停止了
            if (index == len - 2) {
                sum += Math.max(current, nums[index + 1]);
                break;
            }
            int next = nums[index + 1];
            if (next > current + nums[index + 2]) {
                sum+=next;
                index+=3;
            }else {
                sum+=current;
                index+=2;
            }


        }
        return sum;
    }
}
