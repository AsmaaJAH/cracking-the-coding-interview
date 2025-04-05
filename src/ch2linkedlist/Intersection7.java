package ch2linkedlist;

//Q-- not easy:
//Q-- garbage error in output printing 
public class Intersection7 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

      
    }

    static class Result {
        Node tail;
        int size;

        Result(Node tail, int size) {
            this.tail = tail;
            this.size = size;
        }
    }

    static Result getTailAndSize(Node head) {
        if (head == null) return null;

        int size = 1;
        Node current = head;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return new Result(current, size);
    }

    static Node getKthNode(Node head, int k) {
        Node current = head;
        while (k > 0 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }

    static Node findIntersection(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        Result result1 = getTailAndSize(head1);
        Result result2 = getTailAndSize(head2);

        if (result1.tail != result2.tail) {
            return null;
        }

        Node shorter = result1.size < result2.size ? head1 : head2;
        Node longer = result1.size < result2.size ? head2 : head1;

        longer = getKthNode(longer, Math.abs(result1.size - result2.size));

        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        return longer; // or shorter, they’re equal
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // shared part
        Node shared = new Node(7);
        shared.next = new Node(8);
        shared.next.next = new Node(9);

        // First list
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = shared;

        // Second list
        Node head2 = new Node(3);
        head2.next = shared; // same reference

        System.out.println("Intersection by reference:");

        System.out.print("List 1: ");
        printList(head1);
        System.out.print("List 2: ");
        printList(head2);

        Node intersection = findIntersection(head1, head2);
        System.out.println("Intersection at: " + (intersection != null ? intersection.data : "null"));
    }
}

