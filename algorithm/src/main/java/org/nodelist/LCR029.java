package org.nodelist;

public class LCR029 {

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        if (head.next == head) {
            Node node = new Node(insertVal);
            node.next = head;
            head.next = node;
            return head;
        }

        Node cur = head;
        Node next = cur.next;
        Node node = new Node(insertVal);
        do {
            if (insertVal >= cur.val && insertVal <= next.val) {
                cur.next = node;
                node.next = next;
                return  head;
            }
            if (cur.val > next.val) {
                if (insertVal < next.val || insertVal > cur.val) {
                    cur.next = node;
                    node.next = next;
                    return  head;
                }
            }
            cur = next;
            next = next.next;
        } while (cur != head);
        cur.next = node;
        node.next = next;
        return head;
    }

    // 3,4,1
    //2
}
