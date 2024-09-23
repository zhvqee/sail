package org.nodelist;

import java.util.LinkedList;

import static org.nodelist.CommonNodeList.insertNode;

public class LCR026 {

    //输入: head = [1,2,3,4,5]
    //输出: [1,5,2,4,3]
    public void reorderList(ListNode head) {
        ListNode hair = new ListNode();
        ListNode tail = hair;

        ListNode cur = head;

        ListNode next = null;
        LinkedList<ListNode> stack = inputStack(head);
        while (cur != null) {

            next = cur.next;
            cur.next = null;
            tail.next = cur;
            stack.removeLast();

            cur.next = getTail(stack);

            if (next == cur.next) {
                break;
            }
            tail = cur.next;
            cur = next;
        }
        head = hair.next;
        hair.next = null;

    }

    private LinkedList<ListNode> inputStack(ListNode head) {
        LinkedList<ListNode> destack = new LinkedList<>();
        while (head != null) {
            destack.push(head);
            head = head.next;
        }
        return destack;
    }

    //返回tail,和head
    private ListNode getTail(LinkedList<ListNode> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        ListNode pop = stack.pop();
        pop.next = null;
        if (!stack.isEmpty()) {
            stack.peek().next = null;
        }
        return pop;
    }

    public static void main(String[] args) {
        ListNode listNode = insertNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(listNode);
        LCR026 lcr026 = new LCR026();
        lcr026.reorderList(listNode);
    }
}
