package com.example.myapplication.suanfa;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SuanFaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suan_fa);
    }

    public void click230(View view) {
//        boolean valid = isValid2("(({}}))");
//        Toast.makeText(this, valid + "", 1).show();
//        TreeNode treeNode1 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//
//        treeNode1.left =treeNode2;
//        treeNode1.right=treeNode3;
//        treeNode2.left=treeNode4;
//        treeNode2.right=treeNode5;
//        diameterOfBinaryTree(treeNode1);

        int a[] = {2, 1};
        findUnsortedSubarray(a);
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


    class ListNode {
        int val;
        ListNode next;
    }

    //todo 判断环形链表  https://leetcode-cn.com/problems/linked-list-cycle/ 思路是判断节点的地址是否出现过,思路惊奇
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

    //求数组最大和,不能取相邻的数据  https://leetcode-cn.com/problems/house-robber/  todo 再刷  没过
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        int index = 0;
        int sum = 0;
        while (index < len) {
            int current = nums[index];
            if (index == len - 1) {
                sum += current;
                break;
            }
            //没有越界 倒数第二位就停止了
            if (index == len - 2) {
                sum += Math.max(current, nums[index + 1]);
                break;
            }
            int next = nums[index + 1];
            if (next > current + nums[index + 2]) {
                sum += next;
                index += 3;
            } else {
                sum += current;
                index += 2;
            }


        }
        return sum;
    }

    // 判断回文链表  https://leetcode-cn.com/problems/palindrome-linked-list/

    public boolean isPalindrome(ListNode head) {

//        Stack<ListNode> stack = new Stack();
//        while (head != null) {
//            if(!stack.isEmpty()){
//                if(stack.peek().val == head.val){ todo 错误解法:不能用边遍历边抵消的思维   比如特例 1444444444331
//                    stack.pop();
//                    continue;
//                }
//            }
//            stack.push(head);
//            head = head.next;
//        }
//        return stack.isEmpty();

        LinkedList<ListNode> linkedList = new LinkedList<>();
        while (head != null) {
            linkedList.add(head);
            head = head.next;
        }
        while (linkedList.size() > 1) { //todo 有一个元素也算是对称的.....  不是必须偶数个元素
            ListNode node = linkedList.removeFirst();
            if (!(!linkedList.isEmpty() && node.val == linkedList.removeLast().val)) {
                return false;
            }
        }
        return true;

    }

    //https://leetcode-cn.com/problems/path-sum-iii/  二叉树路径和等于指定的数  todo 再刷
    int num = 0;

    public int pathSum(TreeNode root, int sum) {

        if (root == null) {
            return sum == 0 ? 1 : 0;
        }

        int equalsNum = sum(root, 0, sum);
        num += equalsNum;
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return num;
    }

    int sum(TreeNode curNode, int curSum, int sum) {
        if (curNode == null) {
            return 0;
        }
        int newSum = curNode.val + curSum;

        int times = sum(curNode.left, newSum, sum) + sum(curNode.right, newSum, sum);

        return (newSum == sum) ? (times + 1) : times;//todo 路径和即使等于指定数字后,后面的还要继续加,后面的数字的和为0也满足条件. 路径和大于了指定数也要继续加,后面可能为负数
    }

//    [5,4,8,11,null,13,4,7,2,null,null,5,1]
//            22

    //https://leetcode-cn.com/problems/path-sum-iii/  二叉树路径和等于指定的数 第二种解法  todo 再刷
    int number;
    //key 所有的和  value  每一个和的个数
    HashMap<Integer, Integer> map = new HashMap<>();

    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        map.put(0, 1);//todo 前缀和的概念  这个很重要. 每个节点都是一个路径. 默认有一个前缀和为0的数据
        return curSum(root, sum, 0);


    }

    public int curSum(TreeNode node, int sum, int curSum) {
        if (node == null) {
            return number;
        }
        int newSum = curSum + node.val;
        Integer times = map.get(newSum - sum);
        if (times != null) {
            number += times;
        }
        //这个和的个数
        Integer integer = map.get(newSum);
        if (integer == null) {
            integer = 0;
        }

        map.put(newSum, integer + 1);

        curSum(node.left, sum, newSum);


        curSum(node.right, sum, newSum);

        map.put(newSum, integer);
        return number;
    }

    //https://leetcode-cn.com/problems/convert-bst-to-greater-tree/   把二叉搜索树转换为累加树  todo 逻辑绕  重新刷
    public TreeNode convertBST(TreeNode root) {

        preSum(root, 0);

        return root;

    }

    private int preSum(TreeNode node, int sum) {
        if (node == null) {
            return sum;
        }

        int rightSum = preSum(node.right, sum);

        node.val = node.val + rightSum;

        int leftSum = preSum(node.left, node.val);

        return leftSum;

    }

    int maxLength;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return maxLength;
        }
        diameterOfBinaryTree(root.left);
        int currentLeftLength = maxLength(root.left);
        int currentRightLength = maxLength(root.right);
        int currentAllLength = currentLeftLength + currentRightLength + 2;
        maxLength = maxLength > currentAllLength ? maxLength : currentAllLength;
        diameterOfBinaryTree(root.right);
        return maxLength;

    }

    public int maxLength(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftLength = maxLength(node.left);
        int rightLength = maxLength(node.right);
        return Math.max(leftLength, rightLength) + 1;
    }

    public int findUnsortedSubarray(int[] nums) {
        int leftMaxIndex = -1;
        int curNum = nums[0];

        for (int num : nums) {

            if (curNum > num) {
                break;
            }
            leftMaxIndex++;
            curNum = num;

        }
        int length = nums.length;
        int rightMinIndex = length;
        int curNum2 = nums[length - 1];
        for (int i = length - 1; i >= 0; i--) {

            if (curNum2 < nums[i]) {
                break;
            }
            rightMinIndex--;
            curNum2 = nums[i];

        }

        if (leftMaxIndex >= rightMinIndex) {
            return 0;
        }
        int minInRange = nums[leftMaxIndex + 1];
        int maxInRange = nums[leftMaxIndex + 1];
        for (int j = leftMaxIndex + 1; j <= rightMinIndex - 1; j++) {
            int cur = nums[j];
            if (cur > maxInRange) {
                maxInRange = cur;
            }
            if (cur < minInRange) {
                minInRange = cur;
            }
        }
        int answerLeft = leftMaxIndex + 1;
        if (leftMaxIndex == -1) {
            answerLeft = 0;
        } else {

            int realMin = Math.min(minInRange, nums[rightMinIndex == length ? rightMinIndex - 1 : rightMinIndex]);// TODO: 2020-07-18
            for (int m = leftMaxIndex; m > 0; m--) {
                if (nums[m - 1] <= realMin) {
                    answerLeft = m;
                    break;
                }
            }
        }

        int answerRight = rightMinIndex - 1;
        if (rightMinIndex == length) {
            answerRight = length - 1;
        } else {
            int realMax = Math.max(maxInRange, nums[leftMaxIndex == -1 ? 0 : leftMaxIndex]);
            for (int n = rightMinIndex; n < length - 1; n++) { // TODO: 2020-07-18
                if (nums[n + 1] >= realMax) {
                    answerRight = n;
                    break;
                }
            }
        }

        return answerRight - answerLeft + 1;


    }

//    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//
//    }

    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        int validIndex = 0;
        for (int i : nums) {
            if (i == nums[validIndex]) {
                continue;
            }
            nums[++validIndex] = i;
        }

        return validIndex + 1;
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int i = len - 1;
        while (i >= 0) {
            if (digits[i] == 9) {
                digits[i] = 0;
                i--;

            } else {
                digits[i]++;
                break;
            }
        }
        if (i == -1) {
            int[] array = new int[len + 1];
            System.arraycopy(digits, 0, array, 1, len);
            array[0]=1;
            return array;
        }
        return digits;
    }

    public int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

//    public int mySqrt(int x) {
//
//    }


}
