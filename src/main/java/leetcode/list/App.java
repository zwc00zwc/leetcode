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
//        listNode.next.next.next.next = new ListNode(5);
//        listNode.next.next.next.next.next = new ListNode(6);
//        listNode.next.next.next.next.next.next = new ListNode(7);


        ListNode temp = swapPairs(listNode);;
        ListNode res = temp;
        temp.next = listNode;
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
}
