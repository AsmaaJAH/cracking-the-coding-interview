package ch2linkedlist;
//Q-- not easy:
class LinkedListNode {
    int data;  
    LinkedListNode next;  
    
    public LinkedListNode(int data, LinkedListNode next) {
        this.data = data;
        this.next = next;
    }
}
public class LoopDetection8 {

        public static LinkedListNode getLoopBegining(LinkedListNode head) {
            LinkedListNode slow = head;
            LinkedListNode fast = head; 
            
            //collision point
            while (fast != null && fast.next != null) { 
                slow = slow.next; 
                fast = fast.next.next;
                if (slow == fast) {
                    break;
                }
            }
    
            // Error check
            if (fast == null || fast.next == null) {
                return null;
            }
    
            //loop begining:
            slow = head; 
            while (slow != fast) { 
                slow = slow.next; 
                fast = fast.next; 
            }
            
            return fast;
        }
        public static void main(String[] args) {
    
            LinkedListNode head = new LinkedListNode(0, null);
            
            LinkedListNode second = new LinkedListNode(1, null);
            head.next = second;
            
            LinkedListNode third = new LinkedListNode(2, null);
            second.next = third;
            
            LinkedListNode fourth = new LinkedListNode(3, null);
            third.next = fourth;
            
            LinkedListNode fifth = new LinkedListNode(4, null);
            fourth.next = fifth;
            
            LinkedListNode sixth = new LinkedListNode(5, null);
            fifth.next = sixth;
            
            LinkedListNode seventh = new LinkedListNode(6, null);
            sixth.next = seventh;
            
            LinkedListNode eighth = new LinkedListNode(7, null);
            seventh.next = eighth;
            
            LinkedListNode ninth = new LinkedListNode(8, null);
            eighth.next = ninth;
            
            LinkedListNode tenth = new LinkedListNode(9, null);
            ninth.next = tenth;
    
            // Now create the loop 
            tenth.next = sixth;  
            
            LinkedListNode loop = getLoopBegining(head);
            if (loop == null) {
                System.out.println("No Cycle.");
            } else {
                System.out.println("Cycle starts at node with data: " + loop.data);
            }
        }           
    
    }


