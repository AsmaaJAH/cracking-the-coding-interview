package ch2linkedlist;

import java.util.LinkedList;

public class SumLists5 {
    // Java's LinkedList is a generic class, meaning it works with objects, not primitive types like int, double, etc.
    public static LinkedList<Integer> addLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        int i=0;
        while (i < list1.size() || i < list2.size() || carry != 0) {
             int value1 = i < list1.size() ? list1.get(i) : 0;
             int value2 = i < list2.size() ? list2.get(i) : 0;
             int sum = value1 + value2 + carry;
             carry = sum / 10;
             result.add(sum % 10);
             i++;
        }
        return result;
        
    }



    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(7);
        list1.add(1);
        list1.add(6); // 617

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(5);
        list2.add(9);
        list2.add(2); // 295

        LinkedList<Integer> result = addLists(list1, list2);

        System.out.println("List A: " + list1);
        System.out.println("List B: " + list2);
        System.out.println("Sum   : " + result);
    }
}
