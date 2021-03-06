package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-18 11:29
 * 二叉树遍历
 * 前序遍历 中左右 自顶而下进行遍历
 * 中序遍历 左中右 自低而上进行遍历
 * 后序遍历 左右中 自低而上进行遍历
 */
public class App {
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t5 = new TreeNode(5);
        root.left = t2;
        root.right = t5;

        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t2.left = t3;
        t2.right = t4;

        TreeNode t6 = new TreeNode(6);
        t5.left = t6;

        flatten(root);
        System.out.println("main");
        //System.out.println(res.val);
        //getMinimumDifference(a);
        //zigzagLevelOrder(a);
        //hasPathSum(a,0);
        //int count = countNodes(treeNode);
        //List<Integer> list = postorderTraversal(treeNode);
        //System.out.println(count);
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        root.left = mirrorTree(root.left);
        root.right = mirrorTree(root.right);
        return root;
    }

    /**
     * 145. 二叉树的后序遍历
     * @param root
     * @return
     */
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
     * 222. 完全二叉树的节点个数
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
     * 112. 路径总和 是否存在路径
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

        //计算和
        temp[0] = temp[0] + root.val;
        if (temp[0] == sum && root.left == null && root.right == null){
            res[0] = true;
        }

        if (root.left != null){
            backPathSum(root.left,sum,temp,res);
            //弹出
            temp[0] = temp[0] - root.left.val;
        }

        if (root.right != null){
            backPathSum(root.right,sum,temp,res);
            //弹出
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

        //计算和
        temp[0] = temp[0] + root.val;
        if (temp[0] == sum && root.left == null && root.right == null){
            return true;
        }

        if (root.left != null){
            if (backPathSum(root.left,sum,temp)){
                return true;
            }
            //如果不是则弹出这个节点
            temp[0] = temp[0] - root.left.val;
        }

        if (root.right != null){
            if (backPathSum(root.right,sum,temp)){
                return true;
            }
            //如果不是则弹出这个节点
            temp[0] = temp[0] - root.right.val;
        }
        return false;

    }


    /**
     * 113. 路径总和 II
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

    /**
     * 700. 二叉搜索树中的搜索
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        while (root !=null){
            if (val == root.val){
                break;
            }
            if (val >root.val){
                root = root.right;
                continue;
            }
            if (val <root.val){
                root = root.left;
                continue;
            }
        }
        return root;
    }

    /**
     * 617. 合并二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //确定终止条件
        if (t1 == null){
            return t2;
        }
        if (t2 == null){
            return t1;
        }
        //单层递归逻辑
        t1.val = t1.val+t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);
        //确定返回值
        return t1;
    }

    /**
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        //给每个节点校验上限和下限
        return backIsValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public boolean backIsValidBST(TreeNode root,long min,long max){
        if (root == null){
            return true;
        }

        if (root.val<=min || root.val>=max){
            return false;
        }

        return backIsValidBST(root.left,min,root.val) && backIsValidBST(root.right,root.val,max);
    }

    /**
     * 530.二叉搜索树的最小绝对差
     * @param root
     * @return
     */
    public static int getMinimumDifference(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        backGetMinimumDifference(root,Integer.MIN_VALUE,Integer.MAX_VALUE,res);
        return res[0];
    }

    public static void backGetMinimumDifference(TreeNode root,int min,int max, int[] res){
        if (root == null){
            return;
        }

        if (min > Integer.MIN_VALUE){
            res[0] = Math.min(res[0],root.val - min);
        }
        if (max<Integer.MAX_VALUE){
            res[0] = Math.min(res[0],max - root.val);
        }

        backGetMinimumDifference(root.left,min,root.val,res);
        backGetMinimumDifference(root.right,root.val,max,res);
    }


    /**
     * 235. 二叉搜索树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //root为null说明该分支没有公共祖先
        if (root == null){
            return null;
        }
        //如果 root==p说明该分支的p q祖先为p
        //如果 root==q说明该分支的p q祖先为p
        if (root == p || root == q){
            return root;
        }
        //寻找root的左分支和有分支的p q祖先
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        //如果做分支祖先和有分支祖先都有值说明pq在root两侧，则root为最小公共祖先
        if (left != null && right != null){
            return root;
        }
        //如果左分支为空，则说明左分支没有pq,pq都在root的右分支，返回右分支的最小公共祖先
        if (left == null){
            return right;
        }
        //如果右分支为空，则说明右分支没有pq,pq都在root的左分支，返回左分支的最小公共祖先
        if (right == null){
            return left;
        }
        //剩下的就剩都为空了，直接返回root
        return root;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //如果为null了进行赋值
        if (root == null){
            return new TreeNode(val);
        }
        //二分法发进行插入值
        if (root.val>val){
            root.left = insertIntoBST(root.left,val);
        }else {
            root.right = insertIntoBST(root.right,val);
        }
        return root;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return root;
        }
        if (root.val == key){
            //左子树为空，直接返回右子树
            if (root.left == null){
                return root.right;
            }
            //右子树为空
            if (root.right == null){
                return root.left;
            }
            //左右子树都不为空,root的左子树要放到右子树最左的子树下
            if (root.left != null && root.right != null){
                TreeNode temp = root.right;
                while (temp.left!=null){
                    temp = temp.left;
                }
                temp.left = root.left;
                return root.right;
            }
        }
        if (root.val>key){
            root.left = deleteNode(root.left,key);
        }else {
            root.right = deleteNode(root.right,key);
        }
        return root;
    }

    /**
     * 669. 修剪二叉搜索树
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null){
            return root;
        }

        //如果当前root值比low小，返回root.right,但是root.right中也会有比low小的
        //需要进行处理trimBST(root.right)
        if (root.val<low){
            return trimBST(root.right,low,high);
        }
        //如果当前root值比high大，返回root.left,但是root.left中也会有比high大的
        //需要进行处理trimBST(root.left)
        if (root.val>high){
            return trimBST(root.left,low,high);
        }

        root.left = trimBST(root.left,low,high);
        root.right = trimBST(root.right,low,high);

        return root;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return binaryToBST(nums,0,nums.length-1);
    }

    public TreeNode binaryToBST(int[] nums,int start,int end){
        if (start>end){
            return null;
        }
        //中间节点作为root
        int mid = (start + end + 1) /2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = binaryToBST(nums,start,mid-1);
        root.right = binaryToBST(nums,mid+1,end);
        return root;
    }

    /**
     * 94. 二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        backInorderTraversal(root,res);
        return res;
    }

    public void backInorderTraversal(TreeNode root, List<Integer> res){
        if (root == null){
            return;
        }
        backInorderTraversal(root.left,res);
        res.add(root.val);
        backInorderTraversal(root.right,res);
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            temp = new ArrayList<>();
            int count = queue.size();
            for (int i = 0;i<count;i++){
                TreeNode treeNode = queue.poll();
                if (treeNode!=null){
                    temp.add(treeNode.val);
                    queue.add(treeNode.left);
                    queue.add(treeNode.right);
                }
            }
            if (temp.size()>0){
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * 404. 左叶子之和
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        int[] res = new int[1];
        backSumOfLeftLeaves(root,res);
        return res[0];
    }

    public static void backSumOfLeftLeaves(TreeNode root, int[] res){
        if (root == null){
            return;
        }
        if (root.left != null && root.left.left==null && root.left.right == null){
            res[0] = res[0] + root.left.val;
        }
        backSumOfLeftLeaves(root.left,res);
        backSumOfLeftLeaves(root.right,res);
    }

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        //遍历左边深度
        int left = maxDepth(root.left);
        //遍历右边深度
        int right = maxDepth(root.right);
        //左右会有重复，取最大值+1即可
        return Math.max(left,right)+1;
    }

    /**
     * 110. 平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return balancedMaxDepth(root) != -1;
    }

    private int balancedMaxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        //获取左节点的深度
        int left = balancedMaxDepth(root.left);
        //获取右节点的深度
        int right = balancedMaxDepth(root.right);
        //如果左节点深度为-1，即左节点不满足平衡二叉树，返回-1
        if(left == -1) {
            return -1;
        }
        //如果右节点深度为-1，即右节点不满足平衡二叉树，返回-1
        if(right == -1) {
            return -1;
        }
        //如果左右深度差大于等于1，即不满足平衡二叉树，返回-1
        if (Math.abs(left - right)>1){
            return -1;
        }
        //满足平衡二叉树返回最大深度
        return Math.max(left, right) + 1;
    }

    /**
     * 1373. 二叉搜索子树的最大键值和
     * @param root
     * @return
     */
    public int maxSumBST(TreeNode root) {
        if (root == null){
            return 0;
        }
        //获取左节点值
        int left = maxSumBST(root.left);
        //获取右节点值
        int right = maxSumBST(root.right);

        if (left > root.val){
            return left;
        }

        if (right < root.val){
            return left;
        }

        return left+right;
    }

    /**
     * 114. 二叉树展开为链表
     * @param root
     */
    public static void flatten(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        flattenList(root,list);

        TreeNode temp = new TreeNode();
        root = temp;
        if (list!=null && list.size()>0){
            for (Integer item:list) {
                temp.right = new TreeNode(item);
                temp.left = null;
                temp = temp.right;
            }
        }
        root = root.right;
    }

    private static void flattenList(TreeNode root,List<Integer> list){
        if (root == null){
            return;
        }
        list.add(root.val);
        flattenList(root.left,list);
        flattenList(root.right,list);
    }
}
