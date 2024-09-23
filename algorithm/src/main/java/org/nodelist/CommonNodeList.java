package org.nodelist;

public class CommonNodeList {

    public static ListNode insertNode(int[] array) {
        ListNode head = null;
        ListNode tail = null;
        for (int n : array) {
            ListNode p = new ListNode(n);
            if (head == null) {
                head = p;
                tail = p;
            } else {
                tail.next = p;
                tail = p;
            }
        }
        return head;
    }
}
