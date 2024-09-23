package org.nodelist;

import static org.nodelist.CommonNodeList.insertNode;

public class LCR027 {

    //输入: head = [1,2,3,4,5]

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        leftNode = head;
        return isPalindromeCore(head);
    }


    private ListNode leftNode;

    private boolean isPalindromeCore(ListNode rightNode) {
        if (rightNode != null) {
            if (!isPalindromeCore(rightNode.next)) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }
            leftNode = leftNode.next;
        }
        return true;
    }

    //这个是正向递归 ,正向递归会超时，在于变量头尾，那么可以进行反向递归
    public boolean palindromeforward(ListNode head, ListNode tail) {
        if (head == null || head.next == tail) {
            return true;
        }
        ListNode p = head;
        while (p != null && p.next != tail) {
            p = p.next;
        }
        if (head.val != p.val) {
            return false;
        }
        return palindromeforward(head.next, p);
    }

    //逆向打印模式 ,让递归栈记录head
   /* public  void print(ListNode head){
        if(head!=null){
            print(head.next);
            System.out.println(head.val);
        }
    }*/
    public static void main(String[] args) {
        ListNode listNode = insertNode(new int[]{1, 2, 1, 2, 1});
        LCR027 lcr027 = new LCR027();
        boolean palindrome = lcr027.isPalindrome(listNode);
    }

    //第二种，直接复制 到一个List，然后前后指针，简单
    //第三种，先找到中间节点，然后反转链表后半段，比较
}
