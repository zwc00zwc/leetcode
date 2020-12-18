package leetcode.tree;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-18 11:27
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {

    }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
