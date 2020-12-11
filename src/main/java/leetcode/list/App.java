package leetcode.list;

import java.util.List;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-10 11:18
 */
public class App {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        listNode.next.next.next.next.next.next = new ListNode(7);

        ListNode listNode2 = new ListNode(100);
        listNode2.next = new ListNode(101);
        listNode2.next.next = new ListNode(102);
        mergeInBetween(listNode,2,4,listNode2);
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
     * 向右旋转链表
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0){
            return head;
        }
        ListNode temp = head;

        ListNode first = head;
        ListNode end;
        int count = 1;
        while (true){
            if (head.next == null){
                end = head;
                head.next = temp;
                break;
            }
            head = head.next;
            count++;
        }

        k = k % count;
        k = count - k;

        for (int i= 0;i<k;i++){
            first = first.next;
            end = end.next;
        }
        end.next = null;
        return first;
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
     * 两两交换相邻节点
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
     * 翻转链表
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head==null){
            return head;
        }
        ListNode next = null;
        ListNode newHead = new ListNode(head.val);

        while (head.next!=null){
            next = new ListNode(head.next.val);
            next.next = newHead;
            newHead = next;
            head = head.next;
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
}
