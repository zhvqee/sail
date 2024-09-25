package org.nodelist;

public class LCR028 {

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;


    }

    ;

    public static Node insert(int[] nums) {
        Node head = null;
        Node tail = null;
        for (int n : nums) {
            if (head == null) {
                head = new Node();
                head.val = n;
                tail = head;
            } else {
                Node node = new Node();
                node.val = n;
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }
        return head;
    }

    public static Node findNode(Node head, int v) {
        while (head.val != v) {
            head = head.next;
        }
        return head;
    }

    public static Node construct2() {
        Node head1 = insert(new int[]{1, 2});
        Node head2 = insert(new int[]{3});
        Node node = findNode(head1, 1);
        node.child = head2;
        return head1;
    }

    public static Node construct() {
        Node head1 = insert(new int[]{1, 2, 3, 4, 5, 6});
        Node head2 = insert(new int[]{7, 8, 9, 10});
        Node head3 = insert(new int[]{11, 12});
        Node node = findNode(head1, 3);
        node.child = head2;
        Node node1 = findNode(head2, 8);
        node1.child = head3;

        return head1;
    }

    public static void main(String[] args) {
        Node construct = construct();
        System.out.println();
        LCR028 lcr028 = new LCR028();
        Node flatten = lcr028.flatten(construct);

        Node p = flatten;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();

        Node tail = getTail(flatten);
        p = tail;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.prev;
        }
        System.out.println();
    }


    public static Node getTail(Node head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    //[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node p = head;
        while (p != null) {
            if (p.child == null) {
                p = p.next;
            } else {
                Node child = p.child;
                p.child = null;
                Node prev = p;
                Node next = p.next;

                Node node = flatten(child);
                Node tail = getTail(node);

                prev.next = node;
                node.prev = prev;

                if (next != null) {
                    next.prev = tail;
                }

                if (tail != null) {
                    tail.next = next;
                }

                p = next;
            }
        }
        return head;
    }
}
