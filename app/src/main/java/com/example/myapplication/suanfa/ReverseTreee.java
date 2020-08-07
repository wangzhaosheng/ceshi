package com.example.myapplication.suanfa;

/**
 * Created by wangzhaosheng on 2020-06-30
 * Description
 */
public class ReverseTreee {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        change(left,right);
        return root;
    }

    public void change(TreeNode left,TreeNode right ){
        TreeNode temp = left;
        left=right;
        right=temp;
        if(left == null || right==null){
            return ;
        }
        change(left.left,right.right);
        change(left.right,right.left);

    }
}
