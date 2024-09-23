package org.nodelist;

import java.util.LinkedList;

import static org.nodelist.CommonNodeList.insertNode;

public class LCR025 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }
        LinkedList<ListNode> s1 = inputStack(l1);
        LinkedList<ListNode> s2 = inputStack(l2);

        int flag = 0;
        ListNode head = new ListNode();
        while (!s1.isEmpty() && !s2.isEmpty()) {
            ListNode n1 = s1.pop();
            ListNode n2 = s2.pop();
            int val = (n1.val + n2.val + flag) % 10;
            flag = (n1.val + n2.val + flag) / 10;
            ListNode node = new ListNode(val);
            node.next = head.next;
            head.next = node;
        }
        while (!s1.isEmpty()) {
            ListNode n1 = s1.pop();
            int val = (n1.val + flag) % 10;
            flag = (n1.val + flag) / 10;
            ListNode node = new ListNode(val);
            node.next = head.next;
            head.next = node;
        }

        while (!s2.isEmpty()) {
            ListNode n2 = s2.pop();
            int val = (n2.val + flag) % 10;
            flag = (n2.val + flag) / 10;
            ListNode node = new ListNode(val);
            node.next = head.next;
            head.next = node;
        }
        if (flag != 0) {
            ListNode node = new ListNode(flag);
            node.next = head.next;
            head.next = node;
        }
        ListNode newHead = head.next;
        head.next = null;
        return newHead;
    }

    private LinkedList<ListNode> inputStack(ListNode head) {
        LinkedList<ListNode> destack = new LinkedList<>();
        while (head != null) {
            destack.push(head);
            head = head.next;
        }
        return destack;
    }


    public static void main(String[] args) {
        ListNode listNode = insertNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(listNode);

    }
}
