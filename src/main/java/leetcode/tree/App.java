package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-18 11:29
 */
public class App {
    public static void main(String[] args){
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode11.left = treeNode7;
        treeNode11.right = treeNode2;

        TreeNode treeNode41 = new TreeNode(4);
        TreeNode treeNode51 = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1);
        treeNode41.left = treeNode51;
        treeNode41.right = treeNode1;

        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode13 = new TreeNode(13);
        treeNode8.left = treeNode13;
        treeNode8.right = treeNode41;

        TreeNode treeNode4 = new TreeNode(4);
        treeNode4.left = treeNode11;

        TreeNode treeNode = new TreeNode(5);
        treeNode.left = treeNode4;
        treeNode.right = treeNode8;


        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);

        c.left = d;
        c.right = e;
        a.left = b;
        a.right = c;
        zigzagLevelOrder(a);
        //hasPathSum(a,0);
        //int count = countNodes(treeNode);
        //List<Integer> list = postorderTraversal(treeNode);
        //System.out.println(count);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        back(list,root);
        return list;
    }

    public static void back(List<Integer> list, TreeNode treeNode){
        if (treeNode == null){
            return;
        }
        back(list,treeNode.left);
        back(list,treeNode.right);
        list.add(treeNode.val);
    }

    /**
     * 二叉树节点数
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        int[] count = new int[]{0};
        backCountNodes(root,count);
        return count[0];
    }

    public static void backCountNodes(TreeNode root, int[] count){
        if (root == null){
            return;
        }
        count[0]++;
        backCountNodes(root.left,count);
        backCountNodes(root.right,count);
    }

    /**
     * 路径总和 是否存在路径
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        boolean[] res = new boolean[1];
        int[] temp = new int[1];
        backPathSum(root,sum,temp,res);
        return res[0];

//        int[] temp = new int[1];
//        return backPathSum(root,sum,temp);
    }

    /**
     * 路径总和 无返回值递归函数，需要搜索整颗数的时候采用
     * @param root
     * @param sum
     * @param temp
     * @param res
     */
    public static void backPathSum(TreeNode root,int sum,int[] temp, boolean[] res){
        if (root == null){
            return;
        }

        temp[0] = temp[0] + root.val;
        if (temp[0] == sum && root.left == null && root.right == null){
            res[0] = true;
        }

        if (root.left != null){
            backPathSum(root.left,sum,temp,res);
            temp[0] = temp[0] - root.left.val;
        }

        if (root.right != null){
            backPathSum(root.right,sum,temp,res);
            temp[0] = temp[0] - root.right.val;
        }

    }

    /**
     * 路径总和 有返回值递归函数，当无需搜索整个数的时候采用
     * @param root
     * @param sum
     * @param temp
     * @return
     */
    public static boolean backPathSum(TreeNode root,int sum,int[] temp){
        if (root == null){
            return false;
        }

        temp[0] = temp[0] + root.val;
        if (temp[0] == sum && root.left == null && root.right == null){
            return true;
        }

        if (root.left != null){
            if (backPathSum(root.left,sum,temp)){
                return true;
            }
            temp[0] = temp[0] - root.left.val;
        }

        if (root.right != null){
            if (backPathSum(root.right,sum,temp)){
                return true;
            }
            temp[0] = temp[0] - root.right.val;
        }
        return false;

    }


    /**
     * 查找二叉树路径之和
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int[] targe = new int[1];
        backPathSumNodeTree(root,sum,targe,list,res);
        return res;
    }

    public static void backPathSumNodeTree(TreeNode root, int sum, int[] targe,List<Integer> list,List<List<Integer>> res){
        if (root == null){
            return;
        }

        targe[0] = targe[0] + root.val;
        list.add(root.val);
        if (targe[0] == sum && root.left == null && root.right == null){
            List<Integer> v = new ArrayList<>();
            for (Integer item:list) {
                v.add(item);
            }
            res.add(v);
        }

        if (root.left != null){
            backPathSumNodeTree(root.left,sum,targe,list,res);
            targe[0] = targe[0] - root.left.val;
            list.remove(list.size()-1);
        }

        if (root.right != null){
            backPathSumNodeTree(root.right,sum,targe,list,res);
            targe[0] = targe[0] - root.right.val;
            list.remove(list.size()-1);
        }
    }


    /**
     * 103. 二叉树的锯齿形层序遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //定义左边处理栈
        Stack<TreeNode> leftStack = new Stack<>();
        //定义右边处理栈
        Stack<TreeNode> rightStack = new Stack<>();
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = null;
        leftStack.add(root);
        while (!leftStack.isEmpty() || !rightStack.isEmpty()){
            list = new ArrayList<>();
            //从左读到右
            while (!leftStack.isEmpty()){
                TreeNode temp = leftStack.pop();
                if (temp == null){
                    continue;
                }
                list.add(temp.val);
                rightStack.push(temp.left);
                rightStack.push(temp.right);
            }
            if (list != null && list.size()>0){
                lists.add(list);
                continue;
            }
            //从右读到左
            while (!rightStack.isEmpty()){
                TreeNode temp = rightStack.pop();
                if (temp == null){
                    continue;
                }
                list.add(temp.val);
                leftStack.push(temp.right);
                leftStack.push(temp.left);
            }
            if (list != null && list.size()>0){
                lists.add(list);
                continue;
            }
        }
        return lists;
    }
}
