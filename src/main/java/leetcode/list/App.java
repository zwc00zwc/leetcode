package leetcode.list;

import java.util.List;
import java.util.Stack;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-10 11:18
 */
public class App {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(3);
//        listNode.next.next.next = new ListNode(4);
//        listNode.next.next.next.next = new ListNode(5);
//        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = new ListNode(7);

        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);
//        addTwoNumbers(listNode,listNode2);
//        ListNode temp = swapPairs(listNode);;
//        ListNode res = temp;
        //temp.next = listNode;
        System.out.println("---------");
        //ListNode res = rotateRight(listNode,2);
        //int r = kthToLast(listNode,3);
        //System.out.println(r);
    }


    /**
     * 返回倒数第k个节点值
     * @param head
     * @param k
     * @return
     */
    public static int kthToLast(ListNode head, int k) {
        ListNode listNode = head;
        ListNode valNode = head;
        int i = 1;
        while (true){
            if (i > k){
                valNode = valNode.next;
            }
            listNode = listNode.next;
            if (listNode == null){
                return valNode.val;
            }
            i++;
        }
    }

    /**
     * 返回倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode listNode = head;
        ListNode valNode = head;
        int i = 1;
        while (true){
            if (i > k){
                valNode = valNode.next;
            }
            listNode = listNode.next;
            if (listNode == null){
                return valNode;
            }
            i++;
        }
    }

    /**
     * 61. 旋转链表
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0){
            return head;
        }
        //思路是构成一个环，进行旋转，旋转后将尾节点的next置位null
        //设置尾节点
        ListNode tail = head;
        int count = 1;
        //计算链表个数同时得到尾节点
        while (tail.next != null){
            tail = tail.next;
            count++;
        }
        //尾节点指向头节点，构成链表环
        tail.next = head;

        int i = 0;
        //向右旋转等于反向向下走，品一哈
        k = count - k % count;
        while (i<k){
            head = head.next;
            tail = tail.next;
            i++;
        }
        //将尾节点next置为null
        tail.next = null;
        return head;
    }

    /**
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val<l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    /**
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(0);
        ListNode res = temp;
        while (l1 != null && l2 !=null){
            if (l1.val<l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if (temp!=null && l1 !=null){
            temp.next = l1;
        }
        if (temp!=null && l2 !=null){
            temp.next = l2;
        }
        return res.next;
    }

    /**
     * 24. 两两交换链表中的节点
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        //交换相邻节点
        ListNode next = head.next;
        head.next = next.next;
        next.next = head;

        //后续节点递归处理
        head.next = swapPairs(head.next);
        return next;
    }

    /**
     * 删除倒数第n个节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        head = new ListNode(0,head);
        ListNode r = head;
        ListNode preRemove = head;
        int i = 2;
        while (true){
            if (i>n+2){
                preRemove = preRemove.next;
            }
            r = r.next;
            if (r == null){
                break;
            }
            i++;
        }
        preRemove.next = preRemove.next.next;
        return head.next;
    }

    /**
     * 剑指 Offer 24. 反转链表
     * 头插法
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode newHead = new ListNode(head.val);
        while (head.next!=null){
            //head已经赋值过了，从next开始
            head = head.next;
            //构建新的临时节点
            ListNode temp = new ListNode(head.val);
            //临时节点为新的头节点
            temp.next = newHead;
            //临时头节点重新赋值给新头节点
            newHead = temp;
        }
        return newHead;
    }

    /**
     * 判断回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode forListNode = head;

        if (head==null){
            return true;
        }
        ListNode next = null;
        ListNode newHead = new ListNode(head.val);

        while (head.next!=null){
            next = new ListNode(head.next.val);
            next.next = newHead;
            newHead = next;
            head = head.next;
        }

        boolean res = false;
        while (forListNode!=null && newHead!=null){
            if (forListNode.val!= newHead.val){
                res = false;
                break;
            }
            forListNode = forListNode.next;
            newHead = newHead.next;
            res = true;
        }
        return res;
    }

    /**
     * 分割链表
     * @param root
     * @param k
     * @return
     */
    public static ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] array = new ListNode[k];
        int[] len = new int[k];
        if (root == null){
            return array;
        }
        ListNode head = root;
        int i = 0;
        while (root!=null){
            if (i>=len.length){
                i=0;
            }
            len[i]++;
            root = root.next;
            i++;
        }

        ListNode temp = null;
        i = 0;
        while (head!=null){
            if (len[i]<1){
                i++;
            }
            if (array[i]==null){
                array[i] = new ListNode(head.val);
                temp = array[i];
            }else {
                temp.next = new ListNode(head.val);
                temp = temp.next;
            }
            head = head.next;
            len[i]--;
        }
        return array;
    }

    /**
     * 中间节点
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        ListNode root = head;
        int count = 1;
        while (root.next!=null){
            root = root.next;
            count++;
        }
        int i = count / 2 +1;

        while (i>1){
            head = head.next;
            i--;
        }
        return head;
    }

    /**
     * 合并两个链表
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode root = list1;
        int i = 1;
        while (i<a){
            list1 = list1.next;
            i++;
        }
        ListNode an = list1;
        while (i<b+1){
            list1 = list1.next;
            i++;
        }
        an.next = list2;
        while (list2.next!=null){
            list2 = list2.next;
        }
        list2.next = list1.next;
        return root;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        int pre = 0;
        ListNode res = new ListNode(0);
        ListNode root = res;
        while (l1!=null && l2!=null) {
            temp = l1.val + l2.val + pre;
            pre = 0;
            if (temp >= 10) {
                pre = temp / 10;
                temp = temp % 10;
            }
            res.next = new ListNode(temp);
            res = res.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1!=null){
            temp = l1.val + pre;
            pre = 0;
            if (temp >= 10) {
                pre = temp / 10;
                temp = temp % 10;
            }
            res.next = new ListNode(temp);
            res = res.next;
            l1 = l1.next;
        }

        while (l2!=null){
            temp = l2.val + pre;
            pre = 0;
            if (temp >= 10) {
                pre = temp / 10;
                temp = temp % 10;
            }
            res.next = new ListNode(temp);
            res = res.next;
            l2 = l2.next;
        }

        if (pre > 0){
            res.next = new ListNode(pre);
        }
        return root.next;
    }

    /**
     * 23. 合并K个升序链表
     * 递归解法，每次合并两个直到所有都被合并完
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length<1){
            return null;
        }
        //数组中只有一个说明已经合并完了
        if (lists.length==1){
            return lists[0];
        }
        ListNode f = lists[0];
        ListNode s = lists[1];

        //合并两个有序列表
        ListNode temp = new ListNode(0);
        ListNode res = temp;
        while (f!=null && s!=null){
            if (f.val<s.val){
                temp.next = new ListNode(f.val);
                f = f.next;
            }else {
                temp.next = new ListNode(s.val);
                s = s.next;
            }
            temp = temp.next;
        }
        while (f!=null){
            temp.next = new ListNode(f.val);
            f = f.next;
            temp = temp.next;
        }
        while (s!=null){
            temp.next = new ListNode(s.val);
            s = s.next;
            temp = temp.next;
        }
        //重新构造数组，数量-1
        ListNode[] tempList = new ListNode[lists.length-1];
        int i = 0;
        int k = 2;
        while (i<tempList.length-1){
            tempList[i++] = lists[k++];
        }
        tempList[tempList.length-1] = res.next;
        //递归
        return mergeKLists(tempList);
    }

    /**
     * 25. K 个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head,int k){
        if (head == null){
            return null;
        }

        ListNode tempHead = head;
        //使用栈来进行翻转链表，遍历链表得到k个链表值
        Stack<Integer> stack = new Stack();
        int i = 0;
        while (i<k){
            if (tempHead == null){
                break;
            }
            stack.push(tempHead.val);
            tempHead = tempHead.next;
            i++;
        }
        //如果不足k个，直接返回
        if (i<k){
            return head;
        }
        //进行翻转链表
        ListNode listNode = new ListNode(0);
        ListNode newHead = listNode;
        while (!stack.isEmpty()){
            int v = stack.pop();
            listNode.next = new ListNode(v);
            listNode = listNode.next;
        }
        //递归执行
        listNode.next = reverseKGroup(tempHead,k);
        return newHead.next;
    }

    /**
     * 148. 排序链表
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        // 1、递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode1(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 3、当前层业务操作（合并有序链表）
        return mergeTwoLists1(left, right);
    }

    //  找到链表中间节点（876. 链表的中间结点）
    private static ListNode middleNode1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 合并两个有序链表（21. 合并两个有序链表）
    private static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }
}
